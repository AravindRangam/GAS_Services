package com.kartha.cssp.dao;

import java.util.List;

import com.kartha.cssp.data.ServiceOrderData;
import com.kartha.cssp.data.ServiceRequestData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.ServiceRequestBody;
import com.kartha.cssp.request.TransferRequest;

public interface ServiceOrderDAO {
    ServiceOrderData createServiceOrder(TransferRequest transferRequest, String connectDisconnectTransfer) throws CSSPServiceException;

    Boolean createServiceRequest(ServiceRequestBody serviceRequest) throws CSSPServiceException;

    List<ServiceRequestData> getServiceRequests() throws CSSPServiceException;
}
