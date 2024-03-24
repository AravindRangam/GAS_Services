package com.kartha.cssp.dao;

import com.kartha.cssp.data.AccountData;
import com.kartha.cssp.data.LanderListData;
import com.kartha.cssp.data.UserAccountInfoData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.model.Account;
import com.kartha.cssp.model.UserManagement;
import com.kartha.cssp.utils.CSSPConstants;
import com.mongodb.lang.NonNull;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @SuppressWarnings("null")
    public List<LanderListData> getAccountUser(String accountNumber, String email) throws CSSPServiceException {
        if (checkEmpty(accountNumber) && checkEmpty(email)) {
            throw new CSSPServiceException(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR,
                    CSSPConstants.ACCOUNT_NOT_FOUND_MSG);
        }

        Query userQuery = new Query();
        List<Criteria> criteriaList = new ArrayList<>();

        if (!checkEmpty(email)) {
            criteriaList.add(Criteria.where("userId").is(email.toUpperCase()));
        }

        if (!checkEmpty(accountNumber)) {
            criteriaList.add(Criteria.where("userAccountInfo.accountNumber").is(accountNumber));
            criteriaList.add(Criteria.where("userAccountInfo.rowDeletedInd").is(notDeleteFlag));
        }

        if (!criteriaList.isEmpty()) {
            userQuery.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }

        List<UserManagement> userManagementRow = mongodbTemplate.find(userQuery, UserManagement.class);

        return userManagementRow.stream()
                .filter(Objects::nonNull)
                .map(this::mapToLanderListData)
                .filter(Objects::nonNull) // Filter out null LanderListData objects
                .collect(Collectors.toList());
    }

    private LanderListData mapToLanderListData(UserManagement userManagementRowEachRow) {
        LanderListData landerListDataEachRow = new LanderListData();
        List<AccountData> accountDataList = userManagementRowEachRow.getUserAccountInfo().stream()
                .filter(Objects::nonNull)
                .filter(userAccountInfoDataEachRow -> userAccountInfoDataEachRow.getRowDeletedInd()
                        .equalsIgnoreCase(notDeleteFlag))
                .map(userAccountInfoDataEachRow -> {
                    AccountData accountData = new AccountData();
                    populateAccountData(accountData, userAccountInfoDataEachRow.getAccountNumber());
                    return accountData;
                })
                .filter(accountData -> Objects.nonNull(accountData.getAccountNumber())) // Ensure accountData has a
                                                                                        // non-null accountNumber
                .collect(Collectors.toList());

        if (accountDataList.isEmpty()) {
            return null; // If accountDataList is empty, return null
        }

        landerListDataEachRow.setUserId(userManagementRowEachRow.getUserId());
        landerListDataEachRow.setUserDisabledInd(userManagementRowEachRow.getUserDisabledInd());
        landerListDataEachRow.setDefaultAccountNumber(userManagementRowEachRow.getDefaultAccountNumber());
        landerListDataEachRow.setAccountData(accountDataList);

        return landerListDataEachRow;
    }

    private boolean checkEmpty(String input) {
        return input == null || input.isEmpty();
    }
}
