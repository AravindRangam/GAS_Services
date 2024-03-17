package com.kartha.cssp.service;

import com.kartha.cssp.data.LanderListData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.CsspServiceResponse;

public interface AccountLanderService {

    CsspServiceResponse getAccountList(String userId, Boolean getOnlyDefaultFlag) throws CSSPServiceException;

    CsspListServiceResponse<LanderListData> getAccountUser(String accountNumber) throws CSSPServiceException;

}
