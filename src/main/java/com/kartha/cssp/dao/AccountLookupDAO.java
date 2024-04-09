package com.kartha.cssp.dao;

import com.kartha.cssp.data.AccountLookUpData;
import com.kartha.cssp.request.AccountLookupRequest;
import com.kartha.cssp.request.AccountSearchRequest;

import java.util.List;

public interface AccountLookupDAO {

    List<AccountLookUpData> matchNGetAccountInfo(AccountLookupRequest accountLookupRequest) throws Exception;
    List<AccountLookUpData> searchAccount(AccountSearchRequest accountSearchRequest) throws Exception;

}
