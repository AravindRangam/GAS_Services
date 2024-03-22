package com.kartha.cssp.service;

import com.kartha.cssp.dao.ProgramsServiceDAO;
import com.kartha.cssp.dao.RegistrationDAO;
import com.kartha.cssp.data.AccountEmailData;
import com.kartha.cssp.data.AccountEmailUIDData;
import com.kartha.cssp.data.ValidateUserData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.model.UserManagement;
import com.kartha.cssp.request.*;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.ErrorMessages;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.utils.CSSPConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.swing.text.html.CSS;
import java.util.Objects;

@Slf4j
@Service
public class RegistrationServiceImpl implements RegistrationService {

    RegistrationDAO registrationDAO;

    ProgramsServiceDAO programsServiceDAO;

    EnrollUnenrollRequest enrollUnenrollRequest;

    @Autowired
    public RegistrationServiceImpl(RegistrationDAO registrationDAO,
            ProgramsServiceDAO programsServiceDAO,
            EnrollUnenrollRequest enrollUnenrollRequest) {
        this.registrationDAO = registrationDAO;
        this.programsServiceDAO = programsServiceDAO;
        this.enrollUnenrollRequest = enrollUnenrollRequest;
    }

    public CsspServiceResponse validateUserId(String userId) {
        CsspServiceResponse<ValidateUserData> csspValidateUser = new CsspServiceResponse<ValidateUserData>();
        try {
            csspValidateUser.setData(registrationDAO.validateUserId(userId));
        } catch (Exception e) {
            log.error("Exception in validate user id ", e);
            csspValidateUser.setMessage(new Messages(CSSPConstants.VALIDATE_USER_ID_ERROR,
                    CSSPConstants.FAILED, e.getMessage()));
        }

        return csspValidateUser;
    }

    public CsspServiceResponse createUser(CreateUserRequest createUserRequest) throws CSSPServiceException {
        CsspServiceResponse<String> csspGenericResponse = new CsspServiceResponse<String>();
        try {

            if (registrationDAO.createUser(createUserRequest)) {

                enrollUnenrollRequest.setAccountNumber(createUserRequest.getAccountNumber());
                enrollUnenrollRequest.setEnrollUnenrollFlag(createUserRequest.isEmbEnrolled() ? "Y" : "N");

                programsServiceDAO.updateEMB(enrollUnenrollRequest);

                csspGenericResponse.setData("SUCCESS");
            }

        } catch (CSSPServiceException e) {
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.USER_ACCOUNT_REGISTRATION_EXISTS.equalsIgnoreCase(e.getErrorCode())) {
                csspGenericResponse.setMessage(new Messages(CSSPConstants.USER_ACCOUNT_REGISTRATION_EXISTS,
                        CSSPConstants.FAILED, e.getMessage()));
            } else {
                csspGenericResponse.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED, e.getMessage()));
            }
        } catch (Exception e1) {
            csspGenericResponse.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED, e1.getMessage()));
        }
        return csspGenericResponse;
    }

    public CsspServiceResponse updatePassword(UpdatePasswordRequest updatePasswordRequest) throws CSSPServiceException {
        CsspServiceResponse<String> csspGenericResponse = new CsspServiceResponse<String>();
        try {
            registrationDAO.updatePassword(updatePasswordRequest);
            csspGenericResponse.setData("SUCCESS");
        } catch (Exception e) {
            log.error("Exception in updatePassword ", e);
            csspGenericResponse.setMessage(new Messages(CSSPConstants.USER_NOT_FOUND_ERROR,
                    CSSPConstants.FAILED, CSSPConstants.USER_NOT_FOUND_ERROR_MSG));
        }
        return csspGenericResponse;
    }

    public CsspServiceResponse resetPassword(UpdatePasswordRequest updatePasswordRequest) throws CSSPServiceException {
        CsspServiceResponse<String> csspGenericResponse = new CsspServiceResponse<String>();
        try {
            registrationDAO.updatePassword(updatePasswordRequest);
            csspGenericResponse.setData("SUCCESS");
        } catch (Exception e) {
            log.error("Exception in updatePassword ", e);
            csspGenericResponse.setMessage(new Messages(CSSPConstants.USER_NOT_FOUND_ERROR,
                    CSSPConstants.FAILED, CSSPConstants.USER_NOT_FOUND_ERROR_MSG));
        }
        return csspGenericResponse;
    }

    public CsspServiceResponse forgotUID(ForgotUIDRequest forgotUIDRequest) throws CSSPServiceException {
        CsspServiceResponse<AccountEmailUIDData> csspAccountEmailUIDData = new CsspServiceResponse<AccountEmailUIDData>();
        try {

            csspAccountEmailUIDData.setData(registrationDAO.forgotUID(forgotUIDRequest));

        } catch (CSSPServiceException e) {
            log.error("CSSPServiceException in ForGot UID ", e);
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.ACCOUNT_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                csspAccountEmailUIDData.setMessage(new Messages(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR,
                        CSSPConstants.FAILED, CSSPConstants.ACCOUNT_NOT_FOUND_MSG));
            } else if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.ACCOUNT_SSN_NOT_MATCH_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                csspAccountEmailUIDData.setMessage(new Messages(CSSPConstants.ACCOUNT_SSN_NOT_MATCH_ERROR,
                        CSSPConstants.FAILED, CSSPConstants.ACCOUNT_SSN_MATCH_ERROR_MSG));
            } else {
                csspAccountEmailUIDData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED, e.getMessage()));
            }
        } catch (Exception e) {
            log.error("Exception in ForGot UID ", e);
            csspAccountEmailUIDData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED, e.getMessage()));
        }
        return csspAccountEmailUIDData;
    }

    public CsspListServiceResponse forgotPass(ForgotPassRequest forgotPassRequest) throws CSSPServiceException {
        CsspListServiceResponse<AccountEmailData> csspAccountEmailData = new CsspListServiceResponse<AccountEmailData>();
        try {

            csspAccountEmailData.setData(registrationDAO.forgotPass(forgotPassRequest));

        } catch (CSSPServiceException e) {
            log.error("CSSPServiceException in ForGot password ", e);
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.ACCOUNT_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                csspAccountEmailData.setMessage(new Messages(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR,
                        CSSPConstants.FAILED, CSSPConstants.ACCOUNT_NOT_FOUND_MSG));
            } else {
                csspAccountEmailData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED, e.getMessage()));
            }
        } catch (Exception e) {
            log.error("Exception in ForGot Password ", e);
            csspAccountEmailData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED, e.getMessage()));
        }
        return csspAccountEmailData;
    }

    public CsspListServiceResponse allAdminUsers() throws CSSPServiceException {
        CsspListServiceResponse<UserManagement> allAdminUsersData = new CsspListServiceResponse<UserManagement>();
        try {

            allAdminUsersData.setData(registrationDAO.allAdminUsers());

        } catch (CSSPServiceException e) {
            log.error("CSSPServiceException in allAdminUsers ", e);
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.ACCOUNT_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                allAdminUsersData.setMessage(new Messages(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR,
                        CSSPConstants.FAILED, CSSPConstants.ACCOUNT_NOT_FOUND_MSG));
            } else {
                allAdminUsersData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED, e.getMessage()));
            }
        } catch (Exception e) {
            log.error("Exception in allAdminUsers ", e);
            allAdminUsersData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED, e.getMessage()));
        }
        return allAdminUsersData;
    }

    public CsspServiceResponse getAdminUser(String userId) throws CSSPServiceException {
        CsspServiceResponse<UserManagement> csspServiceResponse = new CsspServiceResponse<UserManagement>();
        try {
            csspServiceResponse.setData(registrationDAO.getAdminUser(userId));
        } catch (Exception e) {
            log.error("Exception in getAdminUser ", e);
            csspServiceResponse.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED, e.getMessage()));
        }
        return csspServiceResponse;
    }

}
