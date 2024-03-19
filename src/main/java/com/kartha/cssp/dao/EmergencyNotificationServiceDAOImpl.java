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
        emerygencyNotifications.setType(notificationsData.getType());
        mongodbTemplate.save(emerygencyNotifications);
        return true;
    }

    public boolean updateNotification(CreateEmergencyRequest notificationsData) throws CSSPServiceException {
        EmerygencyNotifications emerygencyNotifications = new EmerygencyNotifications();
        emerygencyNotifications.setId(notificationsData.getId());
        emerygencyNotifications.setMessage(notificationsData.getMessage());
        emerygencyNotifications.setIsDeleted(notificationsData.getIsDeleted());
        emerygencyNotifications.setCreatedTS(notificationsData.getCreatedTS());
        emerygencyNotifications.setLastUpdatedTS(notificationsData.getLastUpdatedTS());
        emerygencyNotifications.setExpiresOn(notificationsData.getExpiresOn());
        emerygencyNotifications.setType(notificationsData.getType());
        mongodbTemplate.save(emerygencyNotifications);
        return true;
    }

    public boolean deleteNotification(String id) throws CSSPServiceException {
        mongodbTemplate.remove(new Query(Criteria.where("id").is(id)), EmerygencyNotifications.class);
        return true;
    }

    public List<EmergencyNotificationsData> getNotifications(String includeDeleted) throws CSSPServiceException {
        List<EmergencyNotificationsData> emergencyNotificationsDataList = new ArrayList<EmergencyNotificationsData>();
        Query query = new Query();

        if(includeDeleted == null || includeDeleted.isEmpty()){
            query.addCriteria(Criteria.where("isDeleted").is("N"));
        } 

        List<EmerygencyNotifications> emerygencyNotifications = mongodbTemplate.find(query, EmerygencyNotifications.class);

        // iterate through the list and convert to data object
        if(emerygencyNotifications != null && !emerygencyNotifications.isEmpty()) {
            // sort the list by created date

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
                    emergencyNotificationsData.setId(emerygencyNotification.getId());
                    emergencyNotificationsData.setMessage(emerygencyNotification.getMessage());
                    emergencyNotificationsData.setIsDeleted(emerygencyNotification.getIsDeleted());
                    emergencyNotificationsData.setCreatedTS(emerygencyNotification.getCreatedTS());
                    emergencyNotificationsData.setLastUpdatedTS(emerygencyNotification.getLastUpdatedTS());
                    emergencyNotificationsData.setExpiresOn(emerygencyNotification.getExpiresOn());
                    emergencyNotificationsData.setType(emerygencyNotification.getType());
                    try {
                        emergencyNotificationsData.setLastUpdatedTSConverted(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(emerygencyNotification.getLastUpdatedTS()));
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    emergencyNotificationsDataList.add(emergencyNotificationsData);
                    
                }
            }
            emergencyNotificationsDataList.sort((EmergencyNotificationsData e1, EmergencyNotificationsData e2) -> e2.getLastUpdatedTSConverted().compareTo(e1.getLastUpdatedTSConverted()));
        }
        return emergencyNotificationsDataList;
    }

}
