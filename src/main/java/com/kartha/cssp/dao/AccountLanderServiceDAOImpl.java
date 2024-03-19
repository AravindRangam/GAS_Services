package com.kartha.cssp.dao;

import com.kartha.cssp.data.AccountData;
import com.kartha.cssp.data.LanderListData;
import com.kartha.cssp.data.UserAccountInfoData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.model.Account;
import com.kartha.cssp.model.UserManagement;
import com.kartha.cssp.utils.CSSPConstants;
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

public class AccountLanderServiceDAOImpl implements AccountLanderServiceDAO {

    MongoTemplate mongodbTemplate;
    String notDeleteFlag = "N";
    String yesFlag = "Y";
    String accountNumber;

    @Autowired
    public AccountLanderServiceDAOImpl(MongoTemplate mongodbTemplate) {
        this.mongodbTemplate = mongodbTemplate;
    }

    public LanderListData getAccountList(String userId, Boolean getOnlyDefaultFlag) throws Exception {
        LanderListData landerListData = new LanderListData();
        landerListData.setUserId(userId);
        Query userQuery = new Query();
        List<AccountData> accountDataList = new ArrayList<AccountData>();
        if (getOnlyDefaultFlag) {
            userQuery.addCriteria(new Criteria().andOperator(Criteria.where("userId").is(userId.toUpperCase()),
                    Criteria.where("userAccountInfo.defaultAccount").is(yesFlag),
                    Criteria.where("userAccountInfo.rowDeletedInd").is(notDeleteFlag)));

            UserManagement userManagementRow = mongodbTemplate.findOne(userQuery, UserManagement.class);
            if (!Objects.nonNull(userManagementRow)) {
                throw new CSSPServiceException(CSSPConstants.USER_ACCOUNT_NOT_FOUND_ERROR,
                        CSSPConstants.USER_ACCOUNT_NOT_FOUND_ERROR_MSG);
            }
            landerListData.setDefaultAccountNumber(userManagementRow.getDefaultAccountNumber());
        } else {
            userQuery.addCriteria(Criteria.where("userId").is(userId.toUpperCase()));
            UserManagement userManagementRow = mongodbTemplate.findOne(userQuery, UserManagement.class);

            if (!Objects.nonNull(userManagementRow)) {
                throw new CSSPServiceException(CSSPConstants.USER_NOT_FOUND_ERROR,
                        CSSPConstants.USER_NOT_FOUND_ERROR_MSG);
            }
            List<UserAccountInfoData> userAccountInfoDataRows = userManagementRow.getUserAccountInfo();

            userAccountInfoDataRows.stream().filter(Objects::nonNull)
                    .forEach(userAccountInfoDataEachRow -> {
                        AccountData accountData = new AccountData();
                        if (userAccountInfoDataEachRow.getRowDeletedInd().equalsIgnoreCase(notDeleteFlag)) {
                            if (userAccountInfoDataEachRow.getDefaultAccount().equalsIgnoreCase(yesFlag)) {
                                landerListData.setDefaultAccountNumber(userAccountInfoDataEachRow.getAccountNumber());
                            }
                            accountNumber = userAccountInfoDataEachRow.getAccountNumber();

                            populateAccountData(accountData, accountNumber);

                            if (Objects.nonNull(accountData.getAccountNumber())) {
                                accountDataList.add(accountData);
                            } else {
                                throw new CSSPServiceException(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR,
                                        CSSPConstants.ACCOUNT_NOT_FOUND_MSG);
                            }
                        }
                    });
            landerListData.setAccountData(accountDataList);
        }
        return landerListData;
    }

    public void populateAccountData(AccountData accountData, String accountNumber) {

        Query query = new Query();
        query.addCriteria(Criteria.where("accountNumber").is(accountNumber));

        Account accountRow = mongodbTemplate.findOne(query, Account.class);
        if (Objects.nonNull(accountRow)) {
            accountData.setFirstName(accountRow.getFirstName());
            accountData.setLastName(accountRow.getLastName());
            accountData.setAccountNumber(accountRow.getAccountNumber());
            accountData.setPremiseNumber(accountRow.getPremiseNumber());
            accountData.setBusinessPartnerNumber(accountRow.getBusinessPartnerNumber());
            accountData.setContractInfoData(accountRow.getContractInfo().get(0));
            accountData.setInstallationInfo(accountRow.getInstallationInfo());
            accountData.setAddress(accountRow.getAddress());
            accountData.setDigitalCommunicationInfo(accountRow.getDigitalCommunicationInfo());
        }
    }

    public List<LanderListData> getAccountUser(String accountNumber, String email) throws CSSPServiceException {
        List<LanderListData> landerListData = new ArrayList<LanderListData>();
        Query userQuery = new Query();

        if(accountNumber.isEmpty() && email.isEmpty()) {
            throw new CSSPServiceException(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR,
                    CSSPConstants.ACCOUNT_NOT_FOUND_MSG);
        }

        if(!checkEmpty(accountNumber) && !checkEmpty(email)) {
            userQuery.addCriteria(new Criteria().orOperator(Criteria.where("userId").is(email.toUpperCase()),
                    Criteria.where("userAccountInfo.accountNumber").is(accountNumber)));
        }

        if(!checkEmpty(email)) {
            userQuery.addCriteria(Criteria.where("userId").is(email.toUpperCase()));
        }

        if(!checkEmpty(accountNumber)) {
            userQuery.addCriteria(Criteria.where("userAccountInfo.accountNumber").is(accountNumber));
        }

        List<UserManagement> userManagementRow = mongodbTemplate.find(userQuery, UserManagement.class);

        // loop throw the userManagementRow and get the userAccountInfoData and populate the landerListData
        userManagementRow.stream().filter(Objects::nonNull)
                .forEach(userManagementRowEachRow -> {
                    LanderListData landerListDataEachRow = new LanderListData();
                    List<AccountData> accountDataList = new ArrayList<AccountData>();
                    landerListDataEachRow.setUserId(userManagementRowEachRow.getUserId());
                    landerListDataEachRow.setDefaultAccountNumber(userManagementRowEachRow.getDefaultAccountNumber());
                    List<UserAccountInfoData> userAccountInfoDataRows = userManagementRowEachRow.getUserAccountInfo();

                    userAccountInfoDataRows.stream().filter(Objects::nonNull)
                            .forEach(userAccountInfoDataEachRow -> {
                                AccountData accountData = new AccountData();
                                if (userAccountInfoDataEachRow.getRowDeletedInd().equalsIgnoreCase(notDeleteFlag)) {
                                    populateAccountData(accountData, userAccountInfoDataEachRow.getAccountNumber());
                                    if (Objects.nonNull(accountData.getAccountNumber())) {
                                        accountDataList.add(accountData);
                                    } else {
                                        throw new CSSPServiceException(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR,
                                                CSSPConstants.ACCOUNT_NOT_FOUND_MSG);
                                    }
                                }
                            });
                    landerListDataEachRow.setAccountData(accountDataList);
                    landerListData.add(landerListDataEachRow);
                });


        return landerListData;
    }

    private boolean checkEmpty(String input) {
        return input == null || input.isEmpty();
    }
}
