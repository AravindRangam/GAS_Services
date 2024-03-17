package com.kartha.cssp.service;

import com.kartha.cssp.dao.CognitoDAOImpl;
import com.kartha.cssp.dao.RegistrationDAO;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.UpdateUserIdRequest;
import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.utils.CSSPConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PreferenceServiceImpl implements PreferenceService {

    @Autowired
    RegistrationDAO registrationDAO;

    public CsspServiceResponse updateUserId(UpdateUserIdRequest updateUserIdRequest, String userId) throws CSSPServiceException {
        CsspServiceResponse<String> csspGenericResponse = new CsspServiceResponse<String>();
        try {
            registrationDAO.updateUserId(updateUserIdRequest, userId);
            csspGenericResponse.setData("SUCCESS");
        } catch (CSSPServiceException e) {
            log.error("CSSPServiceException in createUser ", e);
            csspGenericResponse.setMessage(new Messages(CSSPConstants.USER_NOT_FOUND_ERROR,
                    CSSPConstants.FAILED,CSSPConstants.USER_NOT_FOUND_ERROR_MSG));
        } catch (Exception e) {
            log.error("Exception in createUser ", e);
            csspGenericResponse.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e.getMessage()));
        }
        return csspGenericResponse;
    }

}
