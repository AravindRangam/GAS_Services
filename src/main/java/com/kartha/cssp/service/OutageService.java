package com.kartha.cssp.service;

import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.OutageServiceRequest;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.CsspServiceResponse;

public interface OutageService {

    CsspListServiceResponse validateOutageAddress(OutageServiceRequest outageServiceRequest) throws CSSPServiceException;

    CsspServiceResponse saveOutageDetails(OutageServiceRequest outageServiceRequest) throws CSSPServiceException;
}
