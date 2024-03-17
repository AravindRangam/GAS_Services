package com.kartha.cssp.dao;

import com.kartha.cssp.data.AccountData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.OutageServiceRequest;

import java.util.List;

public interface OutageServiceDAO {
    List<AccountData> validateOutageAddress(OutageServiceRequest outageServiceRequest) throws CSSPServiceException;

    String saveOutageDetails(OutageServiceRequest outageServiceRequest) throws CSSPServiceException;

    void retrieveOutageDetails(AccountData accountData) throws CSSPServiceException;
}
