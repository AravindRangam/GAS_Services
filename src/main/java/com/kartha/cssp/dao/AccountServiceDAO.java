package com.kartha.cssp.dao;

import com.kartha.cssp.data.AccountData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.model.ViewBill;
import com.kartha.cssp.request.AddRemoveAccountRequest;
import com.kartha.cssp.request.SendEmailRequest;
import com.kartha.cssp.request.ValidateAccountSSNRequest;
import com.kartha.cssp.response.CsspServiceResponse;

public interface AccountServiceDAO {

    AccountData validateAccountSSN(ValidateAccountSSNRequest validateAccountRequest) throws Exception;

    AccountData getAccount(String accountNumber) throws Exception;

    String addRemoveAccount(AddRemoveAccountRequest addRemoveAccountRequest) throws Exception;

    String sendEmail(SendEmailRequest sendEmailRequest) throws Exception;

    ViewBill getBill(String accountNumber) throws Exception;

}
