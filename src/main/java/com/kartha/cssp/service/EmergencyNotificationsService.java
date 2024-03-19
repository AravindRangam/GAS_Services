package com.kartha.cssp.service;

import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.CreateEmergencyRequest;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.CsspServiceResponse;

public interface EmergencyNotificationsService {
    
    CsspServiceResponse createNotification(CreateEmergencyRequest createEmergencyRequest) throws CSSPServiceException;
    CsspServiceResponse updateNotification(CreateEmergencyRequest createEmergencyRequest) throws CSSPServiceException;
    CsspServiceResponse deleteNotification(String id) throws CSSPServiceException;
    CsspListServiceResponse getNotifications(String includeDeleted) throws CSSPServiceException;
}
