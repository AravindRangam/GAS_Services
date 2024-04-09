package com.kartha.cssp.dao;

import com.kartha.cssp.data.*;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.model.Account;
import com.kartha.cssp.model.ServiceOrderDoc;
import com.kartha.cssp.model.ServiceRequests;
import com.kartha.cssp.model.UserManagement;
import com.kartha.cssp.request.ServiceRequestBody;
import com.kartha.cssp.request.TransferRequest;
import com.kartha.cssp.utils.CSSPConstants;
import com.kartha.cssp.utils.CommonUtils;
import com.kartha.cssp.utils.EmailServiceUtil;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.valueOf;

@Slf4j
@Repository
public class ServiceOrderDAOImpl implements ServiceOrderDAO {

    MongoTemplate mongodbTemplate;
    String varDisconnect = "Disconnect";
    String varTransfer = "Transfer";
    String varConnect = "Connect";
    int newAccountNumber = 1075257083;
    int newPremiseNumber = 787600003;
    int newBusinessPartner = 500000003;

    EmailServiceUtil emailServiceUtil;

    @Autowired
    public ServiceOrderDAOImpl(MongoTemplate mongodbTemplate, EmailServiceUtil emailServiceUtil) {
        this.mongodbTemplate = mongodbTemplate;
        this.emailServiceUtil = emailServiceUtil;
    }

    public ServiceOrderData createServiceOrder(TransferRequest transferRequest, String connectDisconnectTransfer)
            throws CSSPServiceException {

        newAccountNumber = 1075257083;
        newPremiseNumber = 787600003;
        newBusinessPartner = 500000003;
        ServiceOrderData serviceOrderData = new ServiceOrderData();
        Account account = new Account();
        if (connectDisconnectTransfer.equalsIgnoreCase(varDisconnect) ||
                connectDisconnectTransfer.equalsIgnoreCase(varTransfer) ||
                connectDisconnectTransfer.equalsIgnoreCase(varConnect)) {

            if (!connectDisconnectTransfer.equalsIgnoreCase(varDisconnect)) {
                Query queryForAccountNumber = new Query();
//            queryForAccountNumber.getSortObject();
//            queryForAccountNumber.with(new Sort(Sort.Direction.DESC, "accountNumber"));
//            queryForAccountNumber.limit(1);
//            queryForAccountNumber.addCriteria(new Criteria().andOperator((Criteria.where("accountNumber").gt(""))));

//            Account maxAccountNumber = this.mongodbTemplate.findOne(queryForAccountNumber, Account.class);

                List<Account> accountList = this.mongodbTemplate.find(queryForAccountNumber, Account.class);

                for (Account accountRow : accountList) {
                    if (newAccountNumber <= valueOf(accountRow.getAccountNumber())) {
                        newAccountNumber = valueOf(accountRow.getAccountNumber()) + 1;
                        newPremiseNumber = valueOf(accountRow.getPremiseNumber()) + 1;
                        newBusinessPartner = valueOf(accountRow.getBusinessPartnerNumber()) + 1;
                    }
                }
            }
            //  For Disconnect and Transfer - changing Status to PFN and Add Move Out Dates
            if (connectDisconnectTransfer.equalsIgnoreCase(varDisconnect) ||
                    connectDisconnectTransfer.equalsIgnoreCase(varTransfer)) {
                Query query = new Query();
                query.addCriteria(new Criteria().andOperator(Criteria.where("accountNumber").is(transferRequest.getDisconnectAccountNumber())));

                account = this.mongodbTemplate.findOne(query, Account.class);

                if (Objects.nonNull(account)) {
                    List<ContractInfoData> contractInfoDataList = account.getContractInfo();
                    ContractInfoData contractInfoData = new ContractInfoData();
                    contractInfoData = contractInfoDataList.get(0);
                    contractInfoData.setAccountStatus("PFN");
                    contractInfoData.setMoveOutDate(transferRequest.getDisconnectDate());

                    List<AddressData> addressDataList = account.getAddress();
                    for (AddressData existingAddressData : addressDataList) {
                        if (existingAddressData.getAddressType().equalsIgnoreCase("MAILING")) {
                            List<AddressData> newAddressDataList = transferRequest.getAddressData();
                            for (AddressData newAddressData : newAddressDataList) {
                                if (newAddressData != null) {
                                    if (newAddressData.getAddressType().equalsIgnoreCase("MAILING")) {
                                        existingAddressData.setAptNo(newAddressData.getAptNo());
                                        existingAddressData.setHouseNo(newAddressData.getHouseNo());
                                        existingAddressData.setStreetName(newAddressData.getStreetName());
                                        existingAddressData.setCity(newAddressData.getCity());
                                        existingAddressData.setState(newAddressData.getState());
                                        existingAddressData.setCountry(newAddressData.getCountry());
                                        existingAddressData.setZip(newAddressData.getZip());
                                        existingAddressData.setUpdatedAddTs(CommonUtils.getCurrentTimeStamp());
//                                        existingAddressData.setAddressType("FINAL");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            throw new CSSPServiceException(CSSPConstants.SERVICE_ORDER_FAILED, CSSPConstants.SERVICE_ORDER_FAILED_MSG);
        }
        UserManagement userManagement = null;

        if (connectDisconnectTransfer.equalsIgnoreCase(varTransfer) ||
                connectDisconnectTransfer.equalsIgnoreCase(varConnect)) {
//            Insert New Document into Account Table

            account.setAccountNumber(Long.toString(newAccountNumber));
            account.setPremiseNumber(Long.toString(newPremiseNumber));
            account.setBusinessPartnerNumber(Long.toString(newBusinessPartner));
            account.setFirstName(transferRequest.getFirstName());
            account.setMiddleName(transferRequest.getMiddleName());
            account.setLastName(transferRequest.getLastName());
            account.setSsnTaxid(transferRequest.getSsnTaxid());

            account.setDigitalCommunicationInfo(transferRequest.getDigitalCommunicationInfo());

            for (DigitalCommunicationData digiLoop : account.getDigitalCommunicationInfo()){
                digiLoop.setCreatedCommTs(CommonUtils.getCurrentTimeStamp());
                digiLoop.setUpdatedCommTs(CommonUtils.getCurrentTimeStamp());
            }

            List<ContractInfoData> contractInfoDataList = new ArrayList<>();
            ContractInfoData contractInfoDataForNewAcct = new ContractInfoData();

            contractInfoDataForNewAcct.setTypeOfBusiness("ELE");
            contractInfoDataForNewAcct.setContractId(String.valueOf(newPremiseNumber));
            contractInfoDataForNewAcct.setAccountStatus("PACT");
            contractInfoDataForNewAcct.setRateType("RES");
            contractInfoDataForNewAcct.setRate("");
            contractInfoDataForNewAcct.setTax("");
            contractInfoDataForNewAcct.setFlatRate("");
            contractInfoDataForNewAcct.setMultiplier("");
            contractInfoDataForNewAcct.setCurrentBillAmount("170.00");
            contractInfoDataForNewAcct.setCurrentBillDate("");
            contractInfoDataForNewAcct.setTotalAmountDue("");
            contractInfoDataForNewAcct.setDueDate("");
            contractInfoDataForNewAcct.setNextBillDate("");
            contractInfoDataForNewAcct.setNextBillEndDate("");
            contractInfoDataForNewAcct.setNextMonthCycleDays("");
            contractInfoDataForNewAcct.setProjBillAmount("0");
            contractInfoDataForNewAcct.setProjBaseCharge("0");
            contractInfoDataForNewAcct.setProjBillKWH("0");
            contractInfoDataForNewAcct.setAsOfDayConsumption("0");
            contractInfoDataForNewAcct.setAsOfDayKWHUsage("0");
            contractInfoDataForNewAcct.setMultiDueFlag(false);
            contractInfoDataForNewAcct.setPastDueFlag(false);
            contractInfoDataForNewAcct.setLastPaymentAmt("0.00");
            contractInfoDataForNewAcct.setLastPaymentDate("0.00");
            contractInfoDataForNewAcct.setLastPendingPaymentAmount("0.00");
            contractInfoDataForNewAcct.setBalanceNotInvoiced(true);
            contractInfoDataForNewAcct.setLastPendingPaymentDate("");
            contractInfoDataForNewAcct.setUninvoicedDeposit("150.00");
            contractInfoDataForNewAcct.setAdjustedPastDue("");
            contractInfoDataForNewAcct.setMoveInDate(transferRequest.getConnectDate());
            contractInfoDataForNewAcct.setMoveOutDate("");

            contractInfoDataList.add(contractInfoDataForNewAcct);

            account.setContractInfo(contractInfoDataList);

            InstallationInfo installationInfo = new InstallationInfo();

            List<InstallationInfoData> installationInfoData = new ArrayList<>();

            InstallationInfoData installationInfoDataSingle = new InstallationInfoData();

            installationInfoDataSingle.setContractID(String.valueOf(newPremiseNumber));
            installationInfoDataSingle.setInstallationNumber("INS00585");
            installationInfoDataSingle.setInstallationTypeCode("200");
            installationInfoDataSingle.setInstallationTypeDesc("Electric");
            installationInfoDataSingle.setInstallationTypeFlag(false);
            installationInfoData.add(installationInfoDataSingle );
            installationInfo.setInstallationInfoData(installationInfoData);
            account.setInstallationInfo(installationInfo);

            account.setAddress(transferRequest.getAddressData());
            for (AddressData addressLoop : account.getAddress()){
                addressLoop.setUpdatedAddTs(CommonUtils.getCurrentTimeStamp());
                addressLoop.setCreatedAddTs(CommonUtils.getCurrentTimeStamp());
            }

//            Insert row into user Management table
            if (StringUtils.isNotBlank(transferRequest.getUserId())) {
                Query queryUserId = new Query();
                queryUserId.addCriteria(new Criteria().andOperator(Criteria.where("userId").is(transferRequest.getUserId().toUpperCase())));

                userManagement = mongodbTemplate.findOne(queryUserId, UserManagement.class);
                if(Objects.nonNull(userManagement)) {
                    List<UserAccountInfoData> userAccountInfoDataList = userManagement.getUserAccountInfo();
                    if (Objects.nonNull(userManagement)) {
                        UserAccountInfoData userAccountInfoData = new UserAccountInfoData();
                        userAccountInfoData.setAccountNumber(Integer.toString(newAccountNumber));
                        userAccountInfoData.setNickName("");
                        userAccountInfoData.setRowCreatedTs(CommonUtils.getCurrentTimeStamp());
                        userAccountInfoData.setRowUpdatedTS(CommonUtils.getCurrentTimeStamp());
                        userAccountInfoData.setRowDeletedInd("N");
                        userAccountInfoData.setUserAccountRelation("");
                        userAccountInfoData.setAccountAddedFrom(connectDisconnectTransfer);
                        userAccountInfoData.setAccountAddedBy(transferRequest.getUserId().toUpperCase());
                        userAccountInfoData.setDefaultAccount("N");
                        userAccountInfoData.setDefaultAccountChangeTS(CommonUtils.getCurrentTimeStamp());
                        userAccountInfoDataList.add(userAccountInfoData);
                    }
                }
                
            }
//            Add new Account into Account_Info Table

        }
//            Insert row into ServiceOrder DB

        ServiceOrderDoc newServiceOrder = new ServiceOrderDoc();

        newServiceOrder.setServiceOrderType(connectDisconnectTransfer);
        newServiceOrder.setServiceOrderNumber(Integer.toString(newAccountNumber) + transferRequest.getDisconnectAccountNumber());
        newServiceOrder.setConnectAcccountNumber(Integer.toString(newAccountNumber));
        newServiceOrder.setCreateTimestamp(CommonUtils.getCurrentTimeStamp());
        newServiceOrder.setDisconnectAccountNumber(transferRequest.getDisconnectAccountNumber());
        newServiceOrder.setUserId(transferRequest.getUserId());
        newServiceOrder.setConnectDate(transferRequest.getConnectDate());
        newServiceOrder.setDisconnectDate(transferRequest.getDisconnectDate());
        newServiceOrder.setUpdateTimestamp(CommonUtils.getCurrentTimeStamp());

        newServiceOrder = this.mongodbTemplate.save(newServiceOrder);

        account = this.mongodbTemplate.save(account);

        if (Objects.nonNull(userManagement)) {
            userManagement = this.mongodbTemplate.save(userManagement);
        }
        serviceOrderData.setUserId(transferRequest.getUserId());
        serviceOrderData.setServiceOrderType(transferRequest.getServiceOrderType());
        serviceOrderData.setConnectAcccountNumber(Integer.toString(newAccountNumber));
        serviceOrderData.setDisconnectAccountNumber(transferRequest.getDisconnectAccountNumber());
        serviceOrderData.setConnectDate(transferRequest.getConnectDate());
        serviceOrderData.setDisconnectDate(transferRequest.getDisconnectDate());
        serviceOrderData.setSsnTaxidIndicator(transferRequest.getSsnTaxidIndicator());
        serviceOrderData.setSsnTaxid(transferRequest.getSsnTaxid());
        serviceOrderData.setPremiseOccupied(transferRequest.getPremiseOccupied());
        serviceOrderData.setPremiseAccessSafe(transferRequest.getPremiseAccessSafe());
        serviceOrderData.setPowerOnOFF(transferRequest.getPowerOnOFF());
        serviceOrderData.setDepositAmt(transferRequest.getDepositAmt());
        serviceOrderData.setServiceCharge(transferRequest.getServiceCharge());
        serviceOrderData.setDigitalCommunicationInfo(account.getDigitalCommunicationInfo());
        serviceOrderData.setAddressData(account.getAddress());
        serviceOrderData.setProgramsData(transferRequest.getProgramsData());
        serviceOrderData.setFirstName(transferRequest.getFirstName());
        serviceOrderData.setMiddleName(transferRequest.getMiddleName());
        serviceOrderData.setLastName(transferRequest.getLastName());
        return serviceOrderData;
    }

    public Boolean createServiceRequest(ServiceRequestBody serviceRequestBody) throws CSSPServiceException {

        ServiceRequests serviceRequests = new ServiceRequests();
        serviceRequests.setPrimaryCategory(serviceRequestBody.getPrimaryCategory());
        serviceRequests.setSecondaryCategory(serviceRequestBody.getSecondaryCategory());
        serviceRequests.setFirstName(serviceRequestBody.getFirstName());
        serviceRequests.setLastName(serviceRequestBody.getLastName());
        serviceRequests.setEmail(serviceRequestBody.getEmail());
        serviceRequests.setPhoneNumber(serviceRequestBody.getPhoneNumber());
        serviceRequests.setAccountNumber(serviceRequestBody.getAccountNumber());
        serviceRequests.setStreetAddress(serviceRequestBody.getStreetAddress());
        serviceRequests.setCity(serviceRequestBody.getCity());
        serviceRequests.setState(serviceRequestBody.getState());
        serviceRequests.setZipCode(serviceRequestBody.getZipCode());
        serviceRequests.setMessage(serviceRequestBody.getMessage());
        serviceRequests.setAttachments(serviceRequestBody.getAttachments());

        serviceRequests = this.mongodbTemplate.save(serviceRequests);

        ServiceRequestData serviceRequestData = new ServiceRequestData();
        serviceRequestData.setPrimaryCategory(serviceRequests.getPrimaryCategory());
        serviceRequestData.setSecondaryCategory(serviceRequests.getSecondaryCategory());
        serviceRequestData.setFirstName(serviceRequests.getFirstName());
        serviceRequestData.setLastName(serviceRequests.getLastName());
        serviceRequestData.setEmail(serviceRequests.getEmail());
        serviceRequestData.setPhoneNumber(serviceRequests.getPhoneNumber());
        serviceRequestData.setAccountNumber(serviceRequests.getAccountNumber());
        serviceRequestData.setStreetAddress(serviceRequests.getStreetAddress());
        serviceRequestData.setCity(serviceRequests.getCity());
        serviceRequestData.setState(serviceRequests.getState());
        serviceRequestData.setZipCode(serviceRequests.getZipCode());
        serviceRequestData.setMessage(serviceRequests.getMessage());
        serviceRequestData.setAttachments(serviceRequests.getAttachments());

        try {
            emailServiceUtil.sendEmailWithAttachment(serviceRequestData.getEmail(), serviceRequestData, serviceRequestData.getAttachments());
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return true;

    }

    public List<ServiceRequestData> getServiceRequests() {
        List<ServiceRequests> serviceRequestsDataList = this.mongodbTemplate.findAll(ServiceRequests.class);
        List<ServiceRequestData> serviceRequestDataList = new ArrayList<>();
        for (ServiceRequests serviceRequests : serviceRequestsDataList) {
            ServiceRequestData serviceRequestData = new ServiceRequestData();
            serviceRequestData.setPrimaryCategory(serviceRequests.getPrimaryCategory());
            serviceRequestData.setSecondaryCategory(serviceRequests.getSecondaryCategory());
            serviceRequestData.setFirstName(serviceRequests.getFirstName());
            serviceRequestData.setLastName(serviceRequests.getLastName());
            serviceRequestData.setEmail(serviceRequests.getEmail());
            serviceRequestData.setPhoneNumber(serviceRequests.getPhoneNumber());
            serviceRequestData.setAccountNumber(serviceRequests.getAccountNumber());
            serviceRequestData.setStreetAddress(serviceRequests.getStreetAddress());
            serviceRequestData.setCity(serviceRequests.getCity());
            serviceRequestData.setState(serviceRequests.getState());
            serviceRequestData.setZipCode(serviceRequests.getZipCode());
            serviceRequestData.setMessage(serviceRequests.getMessage());
            serviceRequestData.setAttachments(serviceRequests.getAttachments());
            serviceRequestDataList.add(serviceRequestData);
        }
        return serviceRequestDataList;
        
    }
}
