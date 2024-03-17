package com.kartha.cssp.dao;

import com.kartha.cssp.data.AccountLookUpData;
import com.kartha.cssp.request.AccountLookupRequest;

import java.util.List;

public interface AccountLookupDAO {

    List<AccountLookUpData> matchNGetAccountInfo(AccountLookupRequest accountLookupRequest) throws Exception;

}
