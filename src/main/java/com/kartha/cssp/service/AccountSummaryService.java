package com.kartha.cssp.service;

import com.kartha.cssp.data.AccountData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.response.CsspServiceResponse;

public interface AccountSummaryService {

    CsspServiceResponse getAccountSummary(String accountNumber, String userId) throws CSSPServiceException;

    AccountData getAccountSummaryList(String accountNumber);
}
