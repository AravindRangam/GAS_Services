package com.kartha.cssp.service;

import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.TransferRequest;
import com.kartha.cssp.response.CsspServiceResponse;

public interface ServiceOrder {

    CsspServiceResponse createServiceOrder(TransferRequest transferRequest, String connectDisconnectTransfer) throws CSSPServiceException;
}
