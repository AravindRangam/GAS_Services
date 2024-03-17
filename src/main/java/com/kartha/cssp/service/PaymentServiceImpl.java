package com.kartha.cssp.service;

import com.kartha.cssp.dao.PaymentServiceDAO;
import com.kartha.cssp.data.BankData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.MakePaymentRequest;
import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.utils.CSSPConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    PaymentServiceDAO paymentServiceDAO;

    @Autowired
    public PaymentServiceImpl(PaymentServiceDAO paymentServiceDAO) {
        this.paymentServiceDAO = paymentServiceDAO;
    }

    public CsspServiceResponse retrieveBankDetails(String accountNumber, String requestType) throws CSSPServiceException {
        CsspServiceResponse<BankData> csspBankData = new CsspServiceResponse<BankData>();
        try {
            BankData bankData = new BankData();
            csspBankData.setData(paymentServiceDAO.retrieveBankDetails(accountNumber, requestType, bankData));
        } catch (Exception e) {
            log.error("Exception in Retrieve Bank Info ",e);
            csspBankData.setMessage(new Messages(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR,
                    CSSPConstants.FAILED,CSSPConstants.PAYMENT_DETAILS_NOT_EXIST));
        }
        return csspBankData;
    }

    public CsspServiceResponse makePaymentPayNow(MakePaymentRequest makePaymentRequest, String accountNumber) throws CSSPServiceException {
        CsspServiceResponse<Object> csspPayData = new CsspServiceResponse<Object>();
        try {
            csspPayData.setData(paymentServiceDAO.makePaymentPayNow(makePaymentRequest, accountNumber));
        } catch (Exception e) {
            log.error("Exception in Payment",e);
            csspPayData.setMessage(new Messages(CSSPConstants.PAYMENT_SUBMIT_ERROR,
                    CSSPConstants.FAILED,CSSPConstants.PAYMENT_SUBMIT_ERROR_MSG));
        }

        return csspPayData;
    }
}
