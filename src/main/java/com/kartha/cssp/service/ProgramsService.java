package com.kartha.cssp.service;

import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.BankDetailsRequest;
import com.kartha.cssp.request.EnrollUnenrollRequest;
import com.kartha.cssp.response.CsspListServiceResponse;

public interface ProgramsService {

    CsspListServiceResponse updateEMB(EnrollUnenrollRequest enrollUnenrollRequest) throws CSSPServiceException;

    CsspListServiceResponse updateBudgetBill(EnrollUnenrollRequest enrollUnenrollRequest) throws CSSPServiceException;

    CsspListServiceResponse updateFlatBill(EnrollUnenrollRequest enrollUnenrollRequest) throws CSSPServiceException;

    CsspListServiceResponse updateAutoPay(BankDetailsRequest bankDetailsRequest) throws CSSPServiceException;

    CsspListServiceResponse updatePayNow(BankDetailsRequest bankDetailsRequest) throws CSSPServiceException;

    CsspListServiceResponse updatePayExtend(EnrollUnenrollRequest enrollUnenrollRequest) throws CSSPServiceException;

}
