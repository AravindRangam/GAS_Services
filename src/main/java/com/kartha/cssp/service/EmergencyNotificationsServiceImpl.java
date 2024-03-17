package com.kartha.cssp.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kartha.cssp.dao.EmergencyNotificationsServiceDAO;
import com.kartha.cssp.data.EmergencyNotificationsData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.model.EmerygencyNotifications;
import com.kartha.cssp.request.CreateEmergencyRequest;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.utils.CSSPConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmergencyNotificationsServiceImpl implements EmergencyNotificationsService {

    EmergencyNotificationsServiceDAO emergencyNotificationsServiceDAO;

    @Autowired
    public EmergencyNotificationsServiceImpl(EmergencyNotificationsServiceDAO emergencyNotificationsServiceDAO) {
        this.emergencyNotificationsServiceDAO = emergencyNotificationsServiceDAO;
    }

    public CsspServiceResponse createNotification(CreateEmergencyRequest createEmergencyRequest)
            throws CSSPServiceException {
        CsspServiceResponse<String> csspServiceResponse = new CsspServiceResponse<String>();
        try {
            if(emergencyNotificationsServiceDAO.createNotification(createEmergencyRequest)) {
                csspServiceResponse.setMessage(new Messages(CSSPConstants.SUCCESS));
                csspServiceResponse.setData("SUCCESS");
            } else {
                csspServiceResponse.setMessage(new Messages(CSSPConstants.FAILED));
            }
            
        } catch (CSSPServiceException e) {
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.USER_ACCOUNT_REGISTRATION_EXISTS.equalsIgnoreCase(e.getErrorCode())) {
                csspServiceResponse.setMessage(new Messages(CSSPConstants.USER_ACCOUNT_REGISTRATION_EXISTS,
                        CSSPConstants.FAILED, e.getMessage()));
            } else {
                csspServiceResponse.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED, e.getMessage()));
            }
        } catch (Exception e1) {
            csspServiceResponse.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED, e1.getMessage()));
        }
        return csspServiceResponse;
    }

    public CsspListServiceResponse<EmergencyNotificationsData> getNotifications() throws CSSPServiceException {
        CsspListServiceResponse<EmergencyNotificationsData> csspListServiceResponse = new CsspListServiceResponse<EmergencyNotificationsData>();
        try {
            csspListServiceResponse.setData(emergencyNotificationsServiceDAO.getNotifications());
            csspListServiceResponse.setMessage(new Messages(CSSPConstants.SUCCESS));
        } catch (CSSPServiceException e) {
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.USER_ACCOUNT_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                csspListServiceResponse.setMessage(new Messages(CSSPConstants.USER_ACCOUNT_NOT_FOUND_ERROR,
                        CSSPConstants.FAILED, e.getMessage()));
            } else {
                csspListServiceResponse.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED, e.getMessage()));
            }
        } catch (Exception e1) {
            csspListServiceResponse.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED, e1.getMessage()));
        }
        return csspListServiceResponse;
    }

}
