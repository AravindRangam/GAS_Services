package com.kartha.cssp.dao;

import com.kartha.cssp.data.AccountData;

public interface AccountSummaryServiceDAO {

    AccountData getAccountSummary(String accountNumber, String userId) throws Exception;

    AccountData getAccountSummaryLite(String accountNumber) throws Exception;
}
