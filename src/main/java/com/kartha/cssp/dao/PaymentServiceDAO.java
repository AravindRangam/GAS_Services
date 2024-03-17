package com.kartha.cssp.dao;

import com.kartha.cssp.data.BankData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.MakePaymentRequest;

public interface PaymentServiceDAO {
        BankData retrieveBankDetails(String accountNumber, String requestType, BankData bankData) throws CSSPServiceException;

        String makePaymentPayNow(MakePaymentRequest makePaymentRequest, String accountNumber) throws  CSSPServiceException;
}
