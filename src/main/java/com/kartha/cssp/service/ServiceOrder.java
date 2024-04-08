package com.kartha.cssp.service;

import java.util.List;

import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.ServiceRequestBody;
import com.kartha.cssp.request.TransferRequest;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.CsspServiceResponse;

public interface ServiceOrder {

    CsspServiceResponse createServiceOrder(TransferRequest transferRequest, String connectDisconnectTransfer) throws CSSPServiceException;

    CsspServiceResponse createServiceRequest(ServiceRequestBody serviceRequestBody) throws CSSPServiceException;

    CsspListServiceResponse getServiceRequests() throws CSSPServiceException;
}
