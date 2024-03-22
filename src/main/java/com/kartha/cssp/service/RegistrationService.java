package com.kartha.cssp.service;

import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.*;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.CsspServiceResponse;

public interface RegistrationService {

    CsspServiceResponse validateUserId(String userId) throws CSSPServiceException;

    CsspServiceResponse createUser(CreateUserRequest createUserRequest) throws CSSPServiceException;

    CsspServiceResponse updatePassword(UpdatePasswordRequest updatePasswordRequest) throws CSSPServiceException;

    CsspServiceResponse forgotUID(ForgotUIDRequest forgotUIDRequest) throws CSSPServiceException;

    CsspListServiceResponse forgotPass(ForgotPassRequest forgotPassRequest) throws CSSPServiceException;

    CsspServiceResponse resetPassword(UpdatePasswordRequest updatePasswordRequest) throws CSSPServiceException;

    CsspListServiceResponse allAdminUsers() throws CSSPServiceException;

    CsspServiceResponse getAdminUser(String userId) throws CSSPServiceException;

}
