package com.kartha.cssp.dao;

import java.util.List;

import com.kartha.cssp.data.EmergencyNotificationsData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.CreateEmergencyRequest;

public interface EmergencyNotificationsServiceDAO {
    
    boolean createNotification(CreateEmergencyRequest notificationsData) throws CSSPServiceException;
    List<EmergencyNotificationsData> getNotifications() throws CSSPServiceException;
    
}
