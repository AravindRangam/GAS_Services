package com.kartha.cssp.service;

import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.MakePaymentRequest;
import com.kartha.cssp.response.CsspServiceResponse;

public interface PaymentService {

    CsspServiceResponse retrieveBankDetails(String accountNumber, String requestType) throws CSSPServiceException;

    CsspServiceResponse makePaymentPayNow(MakePaymentRequest makePaymentRequest, String accountNumber) throws CSSPServiceException;
}
