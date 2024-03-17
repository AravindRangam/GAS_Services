package com.kartha.cssp.service;

import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.UpdateUserIdRequest;
import com.kartha.cssp.response.CsspServiceResponse;

public interface PreferenceService {
    CsspServiceResponse updateUserId(UpdateUserIdRequest updateUserIdRequest, String userId)
            throws CSSPServiceException;
}
