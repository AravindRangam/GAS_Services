package com.kartha.cssp.dao;

import com.kartha.cssp.data.BankData;
import com.kartha.cssp.data.ProgramsData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.BankDetailsRequest;
import com.kartha.cssp.request.EnrollUnenrollRequest;

import java.util.List;

public interface ProgramsServiceDAO {

    List<ProgramsData> updateEMB(EnrollUnenrollRequest enrollUnenrollRequest) throws CSSPServiceException;

    List<ProgramsData> updateBudgetBill(EnrollUnenrollRequest enrollUnenrollRequest) throws CSSPServiceException;

    List<ProgramsData> updateFlatBill(EnrollUnenrollRequest enrollUnenrollRequest) throws CSSPServiceException;

    List<BankData> updateAutoPay(BankDetailsRequest bankDetailsRequest) throws CSSPServiceException;

    List<BankData> updatePayNow(BankDetailsRequest bankDetailsRequest) throws CSSPServiceException;

    List<ProgramsData> updatePayExtend(EnrollUnenrollRequest enrollUnenrollRequest) throws CSSPServiceException;

}
