package com.kartha.cssp.data;

import java.util.Date;

public class EmergencyNotificationsData {
    private String id;
    private String message;
    private String isDeleted;
    private String createdTS;
    private String lastUpdatedTS;
    private String expiresOn;
    private String type;
    private Date lastUpdatedTSConverted;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreatedTS() {
        return createdTS;
    }

    public void setCreatedTS(String createdTS) {
        this.createdTS = createdTS;
    }

    public String getLastUpdatedTS() {
        return lastUpdatedTS;
    }

    public void setLastUpdatedTS(String lastUpdatedTS) {
        this.lastUpdatedTS = lastUpdatedTS;
    }

    public String getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(String expiresOn) {
        this.expiresOn = expiresOn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getLastUpdatedTSConverted() {
        return lastUpdatedTSConverted;
    }

    public void setLastUpdatedTSConverted(Date lastUpdatedTSConverted) {
        this.lastUpdatedTSConverted = lastUpdatedTSConverted;
    }
    
}
