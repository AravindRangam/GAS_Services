package com.kartha.cssp.dao;

import com.kartha.cssp.data.AccountData;
import com.kartha.cssp.data.OutageInfo;
import com.kartha.cssp.data.OutageInfoModel;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.model.Account;
import com.kartha.cssp.request.OutageServiceRequest;
import com.kartha.cssp.utils.CSSPConstants;
import com.kartha.cssp.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
public class OutageServiceDAOImpl implements OutageServiceDAO {

    MongoTemplate mongodbTemplate;
    AccountServiceDAOImpl accountServiceDAOImpl;

    @Autowired
    public OutageServiceDAOImpl(MongoTemplate mongodbTemplate,
                                AccountServiceDAOImpl accountServiceDAOImpl) {
        this.mongodbTemplate = mongodbTemplate;
        this.accountServiceDAOImpl = accountServiceDAOImpl;
    }

    public List<AccountData> validateOutageAddress(OutageServiceRequest outageServiceRequest)
            throws CSSPServiceException {

        Query query = new Query();

        if(Objects.nonNull(outageServiceRequest.getZip())) {
            query.addCriteria(Criteria.where("address.addressType").is("PREMISE").
                    and("address.zip").is(outageServiceRequest.getZip()));
        }

        System.out.println("----input--outageServiceRequest.getPhoneNumber()-----"+outageServiceRequest.getPhoneNumber());
        if (!outageServiceRequest.getPhoneNumber().isEmpty()) {
            query.addCriteria(Criteria.where("digitalCommunicationInfo.communicationType").is("phone_no").
                    and("digitalCommunicationInfo.communicateTo").is(outageServiceRequest.getPhoneNumber()));
        }


        List<Account> accountList = mongodbTemplate.find(query, Account.class);

        for(int i=0;i<accountList.size();i++) {
            Account account = (Account)accountList.get(i);
            System.out.println("--outage list-----"+account.getAccountNumber());
        }

        // Loop through the returned data
        if (accountList.isEmpty()) {
            throw new CSSPServiceException(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR, CSSPConstants.ACCOUNT_NOT_FOUND_MSG);
        }

        List<AccountData> accountDataList = new ArrayList<>();

        if (Objects.nonNull(accountList)) {

            accountList.stream().filter(Objects::nonNull)
                    .forEach(account -> {

                        AccountData accountData = new AccountData();

                        accountServiceDAOImpl.populateAccountData(account, accountData);

                        accountData.setContractInfoData(null);

                        populateOutageInfo(accountData);

                        accountDataList.add(accountData);

                    });
        }

        return accountDataList;
    }

    public void retrieveOutageDetails(AccountData accountData) throws CSSPServiceException {
        populateOutageInfo(accountData);
    }

    public void populateOutageInfo(AccountData accountData) {

        Query query = new Query();
        query.with(Sort.by("createdOn").descending());
        query.addCriteria(Criteria.where("accountNumber").is(accountData.getAccountNumber()));
        List<OutageInfo> outageInfoList = new ArrayList<OutageInfo>();

        List<OutageInfoModel> OutageInfoList = mongodbTemplate.find(query, OutageInfoModel.class);

        OutageInfoList.stream().filter(Objects::nonNull).forEach(outageInfoObj -> {
            OutageInfo outageInfo = new OutageInfo();

            outageInfo.setOutgaeTicketNo(outageInfoObj.getOutgaeTicketNo());
            outageInfo.setOutageType(outageInfoObj.getOutageType());
            outageInfo.setEstimate(outageInfoObj.getEstimate());
            outageInfo.setPhone(outageInfoObj.getPhone());
            outageInfo.setEmail(outageInfoObj.getEmail());
            outageInfo.setTicketStatus(outageInfoObj.getTicketStatus());
            outageInfo.setRestorationEstimate(outageInfoObj.getRestorationEstimate());
            outageInfo.setAlertsOn(outageInfoObj.getAlertsOn());
            outageInfo.setCreatedOn(outageInfoObj.getCreatedOn());
            outageInfo.setLatestInfo(outageInfoObj.getLatestInfo());
            outageInfo.setOutageCause(outageInfoObj.getOutageCause());
            outageInfo.setEquipment(outageInfoObj.getEquipment());
            outageInfo.setNoOfCustomersAffected(outageInfoObj.getNoOfCustomersAffected());
            outageInfo.setDebrisInfo(outageInfoObj.getDebrisInfo());
            outageInfo.setOtherInstructions(outageInfoObj.getOtherInstructions());
            outageInfo.setAccessGateCode(outageInfoObj.getAccessGateCode());
            outageInfo.setAnyPets(outageInfoObj.getAnyPets());

            outageInfoList.add(outageInfo);
        });
        accountData.setOutageInfo(outageInfoList);


    }

    public String saveOutageDetails(OutageServiceRequest outageServiceRequest) throws CSSPServiceException {

        Query query = new Query();
        query.with(Sort.by("createdOn").descending());
        query.limit(1);
        List<OutageInfoModel> OutageInfoList = mongodbTemplate.find(query, OutageInfoModel.class);
        Long outageTicketNumnber = new Long(OutageInfoList.get(0).getOutgaeTicketNo().toString()) + 1;
        OutageInfoModel outageInfoModel = setOutagesModelData(outageServiceRequest);
        outageInfoModel.setOutgaeTicketNo(outageTicketNumnber.toString());
        this.mongodbTemplate.save(outageInfoModel);

        return "SUCCESS";
    }

    private OutageInfoModel setOutagesModelData(OutageServiceRequest outageServiceRequest) {
        OutageInfoModel outageInfoModel = new OutageInfoModel();

        outageInfoModel.setAccountNumber(outageServiceRequest.getAccountNumber());
        outageInfoModel.setPremiseNumber(outageServiceRequest.getPremiseNumber());
        outageInfoModel.setOutageType(outageServiceRequest.getOutageType());
        outageInfoModel.setEstimate("2 Hr");
        outageInfoModel.setPhone(outageServiceRequest.getPhone());
        outageInfoModel.setEmail(outageServiceRequest.getEmail());
        outageInfoModel.setTicketStatus("New");
        outageInfoModel.setRestorationEstimate("1 day 5 hours");
        outageInfoModel.setAlertsOn(outageServiceRequest.getAlertsOn());
        outageInfoModel.setCreatedOn(CommonUtils.getCurrentTimeStamp());
        outageInfoModel.setLatestInfo("work in progress");
        outageInfoModel.setOutageCause("faulty lines");
        outageInfoModel.setEquipment("equipment");
        outageInfoModel.setNoOfCustomersAffected("2300");
        outageInfoModel.setDebrisInfo(outageServiceRequest.getDebrisInfo());
        outageInfoModel.setOtherInstructions(outageServiceRequest.getOtherInstructions());
        outageInfoModel.setAccessGateCode(outageServiceRequest.getAccessGateCode());
        outageInfoModel.setAnyPets(outageServiceRequest.getAnyPets());

        return outageInfoModel;
    }


}
