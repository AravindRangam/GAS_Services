package com.kartha.cssp.dao;

import com.kartha.cssp.data.AccountData;
import com.kartha.cssp.data.EnergyUsageData;
import com.kartha.cssp.data.ProgramsData;
import com.kartha.cssp.data.UserAccountInfoData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.model.*;
import com.kartha.cssp.request.AddRemoveAccountRequest;
import com.kartha.cssp.request.SendEmailRequest;
import com.kartha.cssp.request.ValidateAccountSSNRequest;
import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.utils.CSSPConstants;
import com.kartha.cssp.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository

public class AccountServiceDAOImpl implements AccountServiceDAO {

    MongoTemplate mongodbTemplate;
    AddRemoveAccountRequest addRemoveAccountRequest;
    AccountData accountData;
    SendEmailRequest sendEmailRequest;

    @Autowired
    public AccountServiceDAOImpl(MongoTemplate mongodbTemplate,
                                 AccountData accountData,
                                 AddRemoveAccountRequest addRemoveAccountRequest,
                                 SendEmailRequest sendEmailRequest) {
        this.mongodbTemplate = mongodbTemplate;
        this.accountData = accountData;
        this.addRemoveAccountRequest = addRemoveAccountRequest;
        this.sendEmailRequest = sendEmailRequest;
    }

    public AccountData validateAccountSSN(ValidateAccountSSNRequest validateAccountRequest) throws CSSPServiceException {

        Query query = new Query();
        query.addCriteria(Criteria.where("accountNumber").is(validateAccountRequest.getData().getAccountNumber()));
        List<Account> accountList = mongodbTemplate.find(query, Account.class);

        if (Objects.nonNull(accountList)
                && !accountList.isEmpty()) {

            accountList.stream().filter(Objects::nonNull)
                    .forEach(account -> {
                        if (CommonUtils.validateLast4Digits(account.getSsnTaxid(), validateAccountRequest.getData().getSsn())) {
                            populateAccountData(account, accountData);
                            accountData.setSsn(validateAccountRequest.getData().getSsn());
                        } else {
                            throw new CSSPServiceException(CSSPConstants.ACCOUNT_SSN_NOT_MATCH_ERROR, CSSPConstants.ACCOUNT_SSN_MATCH_ERROR_MSG);
                        }
                    });

            populateProgramInfoData(accountData);

        } else {
            throw new CSSPServiceException(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR, CSSPConstants.ACCOUNT_NOT_FOUND_MSG);
        }
        return accountData;
    }

    public AccountData getAccount(String accountNumber) throws CSSPServiceException {

        Query query = new Query();
        query.addCriteria(Criteria.where("accountNumber").is(accountNumber));
        List<Account> accountList = mongodbTemplate.find(query, Account.class);

        if (Objects.nonNull(accountList)
                && !accountList.isEmpty()) {

            accountList.stream().filter(Objects::nonNull)
                    .forEach(account -> {
                        populateAccountData(account, accountData);
                    });

            populateProgramInfoData(accountData);

            populateMeterBillHistory(accountData);

        } else {
            throw new CSSPServiceException(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR, CSSPConstants.ACCOUNT_NOT_FOUND_MSG);
        }
        return accountData;
    }

    public void populateAccountData(Account account, AccountData accountData) {
        accountData.setAccountNumber(String.valueOf(account.getAccountNumber()));
        accountData.setPremiseNumber(account.getPremiseNumber());
        accountData.setBusinessPartnerNumber(account.getBusinessPartnerNumber());
        accountData.setContractInfoData(account.getContractInfo().get(0));
        accountData.setAddress(account.getAddress());
        accountData.setDigitalCommunicationInfo(account.getDigitalCommunicationInfo());
    }

    public void populateProgramInfoData(AccountData accountData) {

        Query query = new Query();
        query.addCriteria(Criteria.where("accountNumber").is(accountData.getAccountNumber()));

        //Program Info
        List<Programs> programsList = mongodbTemplate.find(query, Programs.class);

        if (Objects.nonNull(programsList)
                && !programsList.isEmpty()) {

            programsList.stream().filter(Objects::nonNull)
                    .forEach(programs -> {
                        List<ProgramsData> programsDataList = programs.getProgramInfo();

                        accountData.setProgramsData(programsDataList);

                    });
        }

    }

    public void populateMeterBillHistory(AccountData accountData) {

        Query query = new Query();
        query.addCriteria(Criteria.where("accountNumber").is(accountData.getAccountNumber()));

        //MeterBillHistory Info
        List<MeterBillHistory> billHistoryList = mongodbTemplate.find(query, MeterBillHistory.class);

        if (Objects.nonNull(billHistoryList)
                && !billHistoryList.isEmpty()) {
            billHistoryList.stream().filter(Objects::nonNull)
                    .forEach(meterBillHistoryRow -> {

//                        List<MeterReadingBillingData> MeterReadingBillingDataList = meterBillHistoryRow.getMeterReadingBilling();
//                        accountData.setMeterReadingBilling(MeterReadingBillingDataList);

                        List<EnergyUsageData> MeterReadingBillingDataList = meterBillHistoryRow.getEnergyUsageData();
                        accountData.setEnergyUsageData(MeterReadingBillingDataList);
                    });
        }
    }


    public String addRemoveAccount(AddRemoveAccountRequest addRemoveAccountRequest) throws CSSPServiceException {
        CsspServiceResponse csspServiceResponse = null;
        String notDeleteFlag = "N";
        Boolean acctExists = Boolean.FALSE;
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(
                Criteria.where("userId").is(addRemoveAccountRequest.getUserId().toUpperCase())
        ));

        UserManagement userManagement = mongodbTemplate.findOne(query, UserManagement.class);

        if (!Objects.nonNull(userManagement)) {
            acctExists = Boolean.FALSE;
        } else {
            for (UserAccountInfoData userAccountInUserManagement : userManagement.getUserAccountInfo()) {
                if (Objects.nonNull(userAccountInUserManagement)) {

                    if (addRemoveAccountRequest.getAddDeleteIndicator().equalsIgnoreCase("ADD") &&
                            userAccountInUserManagement.getAccountNumber().equals(addRemoveAccountRequest.getAccountNumber()) &&
                            (userAccountInUserManagement.getRowDeletedInd().equalsIgnoreCase("N")))
                        throw new CSSPServiceException(CSSPConstants.USER_ACCOUNT_EXISTS_ERROR, CSSPConstants.USER_ACCOUNT_EXISTS_ERROR_MSG);

                    if (userAccountInUserManagement.getAccountNumber().equals(addRemoveAccountRequest.getAccountNumber())) {

                        acctExists = Boolean.TRUE;
                        switch (addRemoveAccountRequest.getAddDeleteIndicator()) {
//                            Code runs when account is a match
                            case "ADD":
//                                THis will reset the delete indicator in the table
                                userAccountInUserManagement.setRowUpdatedTS(CommonUtils.getCurrentTimeStamp());
                                userAccountInUserManagement.setRowDeletedInd("N");
                                break;
                            case "DELETE":
                                if (userAccountInUserManagement.getRowDeletedInd().equalsIgnoreCase("Y"))
                                    throw new CSSPServiceException(CSSPConstants.USER_ACCOUNT_NOT_FOUND_ERROR, CSSPConstants.USER_ACCOUNT_NOT_FOUND_ERROR_MSG);
                                userAccountInUserManagement.setRowDeletedInd("Y");
                                break;
                            default:
                                break;
                        }
                    }
                    if (addRemoveAccountRequest.getSetDefault().equalsIgnoreCase("Y")) {
                        if (userAccountInUserManagement.getAccountNumber().equalsIgnoreCase(addRemoveAccountRequest.getAccountNumber())) {
//Set Defaultflag
                            if (userAccountInUserManagement.getRowDeletedInd().equalsIgnoreCase("Y"))
                                throw new CSSPServiceException(CSSPConstants.USER_ACCOUNT_NOT_FOUND_ERROR, CSSPConstants.USER_ACCOUNT_NOT_FOUND_ERROR_MSG);
                            userManagement.setDefaultAccountNumber(addRemoveAccountRequest.getAccountNumber());
                            userAccountInUserManagement.setDefaultAccount("Y");
                            userAccountInUserManagement.setRowUpdatedTS(CommonUtils.getCurrentTimeStamp());
                        } else if (userAccountInUserManagement.getDefaultAccount().equalsIgnoreCase("Y")) {
//Reset Defaultflag if there is any
                            userManagement.setDefaultAccountNumber("");
                            userAccountInUserManagement.setDefaultAccount("N");
                            userAccountInUserManagement.setRowUpdatedTS(CommonUtils.getCurrentTimeStamp());
                        }
                    }
                }
            }
        }

        userManagement = AddAccountUserManagement(userManagement, addRemoveAccountRequest, acctExists);

        if (Objects.nonNull(userManagement)) {
            this.mongodbTemplate.save(userManagement);
        }

        return "SUCCESS";
    }

    public UserManagement AddAccountUserManagement(UserManagement userManagement, AddRemoveAccountRequest
            addRemoveAccountRequest, boolean acctExists) {

        Query accountQuery = new Query();
        accountQuery.addCriteria(Criteria.where("accountNumber").is(addRemoveAccountRequest.getAccountNumber()));

        Account accountInfo = mongodbTemplate.findOne(accountQuery, Account.class);

        List<UserAccountInfoData> userAccountList = new ArrayList<UserAccountInfoData>();
        UserAccountInfoData userAccountInfoData = new UserAccountInfoData();

        if (Objects.isNull(accountInfo)) {
            throw new CSSPServiceException("ACCOUNT_NOT_FOUND_ERROR", "Account not found.");
        } else {

            userAccountList.addAll(userManagement.getUserAccountInfo());

            if (!acctExists) {
                userAccountInfoData.setAccountNumber(addRemoveAccountRequest.getAccountNumber());
                userAccountInfoData.setNickName("");
                userAccountInfoData.setRowCreatedTs(CommonUtils.getCurrentTimeStamp());
                userAccountInfoData.setRowUpdatedTS(CommonUtils.getCurrentTimeStamp());
                userAccountInfoData.setRowDeletedInd("N");
                userAccountInfoData.setUserAccountRelation("");
                userAccountInfoData.setAccountAddedFrom(addRemoveAccountRequest.getAddedFrom());
                userAccountInfoData.setAccountAddedBy(addRemoveAccountRequest.getUserId().toUpperCase());
                userAccountInfoData.setDefaultAccount(addRemoveAccountRequest.getSetDefault());
                userAccountInfoData.setDefaultAccountChangeTS(CommonUtils.getCurrentTimeStamp());

                userAccountList.add(userAccountInfoData);
            }

            //Common code for both Addaccount and Registration

            userManagement.setUserAccountInfo(userAccountList);
            if (addRemoveAccountRequest.getSetDefault().equals("Y"))
                userManagement.setDefaultAccountNumber(addRemoveAccountRequest.getAccountNumber());
        }

        return userManagement;
    }

    public String sendEmail(SendEmailRequest sendEmailRequest) throws CSSPServiceException {


        EmailLog emailLog = new EmailLog();

        emailLog.setEmailId(sendEmailRequest.getEmailId());
        emailLog.setCcEmailId(sendEmailRequest.getCcEmailId());
        emailLog.setEmailSubject(sendEmailRequest.getEmailSubject());
        emailLog.setEmailType(sendEmailRequest.getEmailType());
        emailLog.setAccountNumber(sendEmailRequest.getAccountNumber());
        emailLog.setEmailBody(sendEmailRequest.getEmailBody());
        emailLog.setFirstName(sendEmailRequest.getFirstName());
        emailLog.setMiddleName(sendEmailRequest.getMiddleName());
        emailLog.setLastName(sendEmailRequest.getLastName());
        emailLog.setUserId(sendEmailRequest.getUserId());
        emailLog.setResetCodeOrURL(sendEmailRequest.getResetCodeOrURL());
        emailLog.setMailingAddress(sendEmailRequest.getMailingAddress());

        emailLog = this.mongodbTemplate.save(emailLog);

        return "SUCCESS";
    }

}
