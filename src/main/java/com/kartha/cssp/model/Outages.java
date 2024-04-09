package com.kartha.cssp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Document(collection = "outages")
public class Outages {

    @Id
    private String id;
    private String latitude;
    private String longitude;
    private String lineOfService;
    private String fullPartial;
    private String message;
    private String restorationEstimatedDate;
    private Boolean serviceRestored;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLineOfService() {
        return lineOfService;
    }

    public void setLineOfService(String lineOfService) {
        this.lineOfService = lineOfService;
    }

    public String getFullPartial() {
        return fullPartial;
    }

    public void setFullPartial(String fullPartial) {
        this.fullPartial = fullPartial;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRestorationEstimatedDate() {
        return restorationEstimatedDate;
    }

    public void setRestorationEstimatedDate(String restorationEstimatedDate) {
        this.restorationEstimatedDate = restorationEstimatedDate;
    }

    public Boolean getServiceRestored() {
        return serviceRestored;
    }

    public void setServiceRestored(Boolean serviceRestored) {
        this.serviceRestored = serviceRestored;
    }
}
