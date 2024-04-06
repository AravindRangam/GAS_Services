package com.kartha.cssp.service;

import com.kartha.cssp.data.AccountData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.AddRemoveAccountRequest;
import com.kartha.cssp.request.ValidateAccountSSNRequest;
import com.kartha.cssp.request.SendEmailRequest;
import com.kartha.cssp.response.CsspServiceResponse;

public interface AccountService {

    CsspServiceResponse validateAccountSSN(ValidateAccountSSNRequest validateAccountRequest) throws CSSPServiceException;

    CsspServiceResponse getAccount(String accountNumber) throws CSSPServiceException;

    CsspServiceResponse addRemoveAccount(AddRemoveAccountRequest addRemoveAccountRequest)  throws CSSPServiceException;

    CsspServiceResponse sendEmail(SendEmailRequest sendEmailRequest) throws CSSPServiceException;

    CsspServiceResponse getBill(String accountNumber) throws CSSPServiceException;

}
