package com.kartha.cssp.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.kartha.cssp.data.EmergencyNotificationsData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.model.EmerygencyNotifications;
import com.kartha.cssp.request.CreateEmergencyRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class EmergencyNotificationServiceDAOImpl implements EmergencyNotificationsServiceDAO {

    MongoTemplate mongodbTemplate;

    public EmergencyNotificationServiceDAOImpl(MongoTemplate mongodbTemplate) {
        this.mongodbTemplate = mongodbTemplate;
    }


    public boolean createNotification(CreateEmergencyRequest notificationsData) throws CSSPServiceException {
        EmerygencyNotifications emerygencyNotifications = new EmerygencyNotifications();
        emerygencyNotifications.setMessage(notificationsData.getMessage());
        emerygencyNotifications.setIsDeleted(notificationsData.getIsDeleted());
        emerygencyNotifications.setCreatedTS(notificationsData.getCreatedTS());
        emerygencyNotifications.setLastUpdatedTS(notificationsData.getLastUpdatedTS());
        emerygencyNotifications.setExpiresOn(notificationsData.getExpiresOn());
        mongodbTemplate.save(emerygencyNotifications);
        return true;
    }

    public List<EmergencyNotificationsData> getNotifications() throws CSSPServiceException {
        List<EmergencyNotificationsData> emergencyNotificationsDataList = new ArrayList<EmergencyNotificationsData>();
        Query query = new Query();

        query.addCriteria(Criteria.where("isDeleted").is("N"));

        List<EmerygencyNotifications> emerygencyNotifications = mongodbTemplate.find(query, EmerygencyNotifications.class);

        // iterate through the list and convert to data object
        if(emerygencyNotifications != null && !emerygencyNotifications.isEmpty()) {
            for(EmerygencyNotifications emerygencyNotification : emerygencyNotifications) {
                Boolean isExpired = false;
                // check if the current notification expired
                if(emerygencyNotification.getExpiresOn() != null) {
                    // convert the string to date and check if it is expired
                    Date expiresOn;
                    try {
                        expiresOn = new SimpleDateFormat("yyyy-MM-dd").parse(emerygencyNotification.getExpiresOn());
                        if(expiresOn.before(new Date())) {
                            isExpired = true;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if(isExpired == false) {
                    // convert to data object and add to the list
                    EmergencyNotificationsData emergencyNotificationsData = new EmergencyNotificationsData();
                    emergencyNotificationsData.setMessage(emerygencyNotification.getMessage());
                    emergencyNotificationsData.setIsDeleted(emerygencyNotification.getIsDeleted());
                    emergencyNotificationsData.setCreatedTS(emerygencyNotification.getCreatedTS());
                    emergencyNotificationsData.setLastUpdatedTS(emerygencyNotification.getLastUpdatedTS());
                    emergencyNotificationsData.setExpiresOn(emerygencyNotification.getExpiresOn());
                    emergencyNotificationsDataList.add(emergencyNotificationsData);
                }
            }
        }
        return emergencyNotificationsDataList;
    }

}
