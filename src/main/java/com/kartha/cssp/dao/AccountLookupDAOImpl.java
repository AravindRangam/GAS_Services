package com.kartha.cssp.dao;

import com.kartha.cssp.data.AccountLookUpData;
import com.kartha.cssp.data.AddressData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.model.Account;
import com.kartha.cssp.request.AccountLookupRequest;
import com.kartha.cssp.utils.CSSPConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class AccountLookupDAOImpl implements AccountLookupDAO {

    MongoTemplate mongodbTemplate;
    List<AccountLookUpData> accountLookUpDataList;
    AccountLookUpData accountLookUpData;

    @Autowired
    public AccountLookupDAOImpl(MongoTemplate mongodbTemplate,
                                AccountLookUpData accountLookUpData) {
        this.mongodbTemplate = mongodbTemplate;
        this.accountLookUpData = accountLookUpData;
    }

    @Override
    public List<AccountLookUpData> matchNGetAccountInfo(AccountLookupRequest accountLookupRequest) throws Exception {
        accountLookUpDataList = new ArrayList<AccountLookUpData>();
        Query query = new Query();
        Criteria criteriaInput = Criteria.where("address.addressType").is("PREMISE");
        criteriaInput.and("address.zip").is(accountLookupRequest.getZipCode());
        if (!accountLookupRequest.getFirstName().isEmpty()) {
            criteriaInput.and("firstName").is(accountLookupRequest.getFirstName().toUpperCase());
            criteriaInput.and("lastName").is(accountLookupRequest.getLastName().toUpperCase());
        } else {
            if (!accountLookupRequest.getPhoneNo().isEmpty()) {
                criteriaInput.and("digitalCommunicationInfo.communicationType").is("phone_no");
                criteriaInput.and("digitalCommunicationInfo.communicateTo").is(accountLookupRequest.getPhoneNo());
            }
        }

        query.addCriteria(criteriaInput);

        List<Account> accountInfoList = mongodbTemplate.find(query, Account.class);

        // Loop through the returned data
        if (accountInfoList.isEmpty()) {
            throw new CSSPServiceException(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR, CSSPConstants.ACCOUNT_NOT_FOUND_MSG);
        }
        for (Account userAccountInfoDataEachRow : accountInfoList) {
            if (userAccountInfoDataEachRow != null) {
                //check in if condition if the SSN is a match, if matched send account data out
                if (accountLookupRequest.getLast4SSN().equalsIgnoreCase(userAccountInfoDataEachRow.getSsnTaxid().substring(5, 9))) {
                    accountLookUpData.setAccountNumber(userAccountInfoDataEachRow.getAccountNumber());
                    accountLookUpData.setBusinessPartnerNumber(userAccountInfoDataEachRow.getBusinessPartnerNumber());
                    accountLookUpData.setFirstName(userAccountInfoDataEachRow.getFirstName());
                    accountLookUpData.setMiddleName(userAccountInfoDataEachRow.getMiddleName());
                    accountLookUpData.setLastName(userAccountInfoDataEachRow.getLastName());
                    accountLookUpData.setPremiseNumber(userAccountInfoDataEachRow.getPremiseNumber());
                    if (userAccountInfoDataEachRow.getAddress().isEmpty()) {
                        throw new CSSPServiceException("ACCOUNT_INFO_INCONSISTENT_ERROR", CSSPConstants.ACCOUNT_NOT_FOUND_MSG);
                    }
                    for (AddressData addressDataEachRow : userAccountInfoDataEachRow.getAddress()) {
                        if (addressDataEachRow != null) {
                            if (addressDataEachRow.getAddressType().equals("PREMISE")) {
                                accountLookUpData.setAddress(addressDataEachRow);
                            }
                        }
                    }
                    accountLookUpDataList.add(accountLookUpData);
                } else {
                    throw new CSSPServiceException(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR, CSSPConstants.ACCOUNT_NOT_FOUND_MSG);
                }
            } else {
                throw new CSSPServiceException(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR, CSSPConstants.ACCOUNT_NOT_FOUND_MSG);
            }
        }
        return accountLookUpDataList;
    }
}
