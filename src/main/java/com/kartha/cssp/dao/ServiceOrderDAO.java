package com.kartha.cssp.dao;

import com.kartha.cssp.data.ServiceOrderData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.TransferRequest;

public interface ServiceOrderDAO {
    ServiceOrderData createServiceOrder(TransferRequest transferRequest, String connectDisconnectTransfer) throws CSSPServiceException;
}
