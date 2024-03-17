package com.kartha.cssp.dao;

import com.kartha.cssp.data.*;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.model.*;
import com.kartha.cssp.utils.CSSPConstants;
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
public class AccountSummaryServiceDAOImpl implements AccountSummaryServiceDAO {

    MongoTemplate mongodbTemplate;
    AccountData accountData;

    @Autowired
    public AccountSummaryServiceDAOImpl(MongoTemplate mongodbTemplate,
                                        AccountData accountData) {
        this.mongodbTemplate = mongodbTemplate;
        this.accountData = accountData;
    }

    public AccountData getAccountSummary(String accountNumber, String userId) throws Exception {
        // Check if UserId and AccountNumber passed existing in user_management Table
        checkUserManagement(accountNumber,userId);

        Query query = new Query();
        query.addCriteria(Criteria.where("accountNumber").is(accountNumber));
        List<Account> accountList = mongodbTemplate.find(query, Account.class);

        if (Objects.nonNull(accountList)
                && !accountList.isEmpty()) {
            accountList.stream().filter(Objects::nonNull)
                    .forEach(account -> {
                        populateAccountData(account, accountData);
                    });

            System.out.println("ENtering Populate Cards");
            populateCardLayout(accountData);

            populateProgramInfoData(accountData);

            populateMeterBillHistory(accountData);

            populateTemperatureData(accountData);

            populateOutageInfoData(accountData);
        }  else {
            throw new CSSPServiceException(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR, CSSPConstants.ACCOUNT_NOT_FOUND_MSG);
        }
        return accountData;
    }

    public AccountData getAccountSummaryLite(String accountNumber) throws Exception {
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

            populateOutageInfoData(accountData);
        }  else {
            throw new CSSPServiceException(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR, CSSPConstants.ACCOUNT_NOT_FOUND_MSG);
        }
        return accountData;
    }


    public void populateCardLayout(AccountData accountData) {
        CardsLayout customCardsLayout= new CardsLayout();
        CardsLayout suggestedCardsLayout = new CardsLayout();
        customCardsLayout.setAccountInfoSeqNo(1);
        customCardsLayout.setAccountOverviewSeqNo(3);
        customCardsLayout.setBillComparisionSeqNo(4);
        customCardsLayout.setBillingProgramsSeqNo(6);
        customCardsLayout.setUsageComparisionSeqNo(2);
        customCardsLayout.setProjectedBillingSeqNo(5);
        customCardsLayout.setAccountInfoFlipAssist(false);
        customCardsLayout.setAccountOverviewFlipAssist(false);
        customCardsLayout.setBillComparisionFlipAssist(false);
        customCardsLayout.setBillingProgramsFlipAssist(false);
        customCardsLayout.setUsageComparisionFlipAssist(false);
        customCardsLayout.setProjectedBillingFlipAssist(false);
        suggestedCardsLayout.setAccountInfoSeqNo(1);
        suggestedCardsLayout.setAccountOverviewSeqNo(3);
        suggestedCardsLayout.setBillComparisionSeqNo(4);
        suggestedCardsLayout.setBillingProgramsSeqNo(6);
        suggestedCardsLayout.setUsageComparisionSeqNo(2);
        suggestedCardsLayout.setProjectedBillingSeqNo(5);
        suggestedCardsLayout.setAccountInfoFlipAssist(false);
        suggestedCardsLayout.setAccountOverviewFlipAssist(false);
        suggestedCardsLayout.setBillComparisionFlipAssist(false);
        suggestedCardsLayout.setBillingProgramsFlipAssist(false);
        suggestedCardsLayout.setUsageComparisionFlipAssist(false);
        suggestedCardsLayout.setProjectedBillingFlipAssist(false);

        accountData.setCustomCardsLayout(customCardsLayout);
        accountData.setSuggestedCardsLayout(suggestedCardsLayout);
    }

    public void populateAccountData(Account account, AccountData accountData) {
        accountData.setAccountNumber(String.valueOf(account.getAccountNumber()));
        accountData.setPremiseNumber(account.getPremiseNumber());
        accountData.setBusinessPartnerNumber(account.getBusinessPartnerNumber());
        accountData.setFirstName(account.getFirstName());
        accountData.setMiddleName(account.getMiddleName());
        accountData.setLastName(account.getLastName());
        accountData.setContractInfoData(account.getContractInfo().get(0));
        accountData.setAddress(account.getAddress());
        accountData.setDigitalCommunicationInfo(account.getDigitalCommunicationInfo());
        accountData.setInstallationInfo(account.getInstallationInfo());
    }

    public void populateOutageInfoData(AccountData accountData) {

        Query query = new Query();
        query.with(Sort.by("createdOn").descending());
        query.limit(1);
        query.addCriteria(Criteria.where("accountNumber").is(accountData.getAccountNumber()));
        List<OutageInfo> outageInfoList = new ArrayList<OutageInfo>();

        List<OutageInfoModel> OutageInfoList = mongodbTemplate.find(query, OutageInfoModel.class);

        OutageInfoList.stream().filter(Objects::nonNull).forEach(outageInfoObj -> {
            if(!outageInfoObj.getTicketStatus().equalsIgnoreCase("Completed")) {
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
            }
        });
        accountData.setOutageInfo(outageInfoList);
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

                        List<EnergyUsageData> MeterReadingBillingDataList = meterBillHistoryRow.getEnergyUsageData();
                        accountData.setEnergyUsageData(MeterReadingBillingDataList);
                    });
        }
    }
    public void populateTemperatureData(AccountData accountData) {
        Query query = new Query();
        query.addCriteria(Criteria.where(""));
        Temperature temperatureData = mongodbTemplate.findOne(query, Temperature.class);
        accountData.setTemperatureDataList(temperatureData.getTemperatureDataList());

        Query query1 = new Query();
        query1.addCriteria(Criteria.where(""));

        NextBillTemperature nextBillTemperatureData = mongodbTemplate.findOne(query1, NextBillTemperature.class);

        TemperatureData temp1 = new TemperatureData();

        temp1.setMonth(nextBillTemperatureData.getMonth());
        temp1.setAvg(nextBillTemperatureData.getAvg());

        accountData.setNextBillTemperature(temp1);
    }
    public void checkUserManagement(String accountNumber, String userId) {

        String notDeleteFlag = "N";
        Query query = new Query();

        query.addCriteria(new Criteria().andOperator(Criteria.where("userId").is(userId.toUpperCase()),
                Criteria.where("userAccountInfo.accountNumber").is(accountNumber),
                Criteria.where("userAccountInfo.rowDeletedInd").is(notDeleteFlag)));

        List<UserManagement> userManagementList = mongodbTemplate.find(query, UserManagement.class);
        System.out.println(userManagementList.toString());
        if (!Objects.nonNull(userManagementList) || userManagementList.isEmpty()) {
                 throw new CSSPServiceException("ACCOUNT_USERID_NOT_FOUND_ERROR", "Invalid Combination."); }
    }

}
