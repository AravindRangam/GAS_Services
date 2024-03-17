package com.kartha.cssp.service;

import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.AccountLookupRequest;
import com.kartha.cssp.request.ValidateAccountSSNRequest;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.CsspServiceResponse;

public interface AccountLookupService {

    CsspListServiceResponse matchNGetAccountInfo(AccountLookupRequest accountLookupRequest)  throws CSSPServiceException;

    CsspServiceResponse validateAccountSSN(ValidateAccountSSNRequest validateAccountRequest) throws CSSPServiceException;
}
