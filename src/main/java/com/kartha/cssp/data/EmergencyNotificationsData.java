package com.kartha.cssp.data;

public class EmergencyNotificationsData {
    private String message;
    private String isDeleted;
    private String createdTS;
    private String lastUpdatedTS;
    private String expiresOn;

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
    
}
