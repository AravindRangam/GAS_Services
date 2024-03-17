package com.kartha.cssp.data;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class OutageInfo implements Serializable {

    private String outgaeTicketNo;
    private String outageType;
    private String estimate;
    private String phone;
    private String email;
    private String ticketStatus;
    private String restorationEstimate;
    private String alertsOn;
    private String createdOn;
    private String latestInfo;
    private String outageCause;
    private String equipment;
    private String NoOfCustomersAffected;
    private String debrisInfo;
    private String otherInstructions;
    private String accessGateCode;
    private String anyPets;

    public String getOutgaeTicketNo() {
        return outgaeTicketNo;
    }

    public void setOutgaeTicketNo(String outgaeTicketNo) {
        this.outgaeTicketNo = outgaeTicketNo;
    }

    public String getOutageType() {
        return outageType;
    }

    public void setOutageType(String outageType) {
        this.outageType = outageType;
    }

    public String getEstimate() {
        return estimate;
    }

    public void setEstimate(String estimate) {
        this.estimate = estimate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getRestorationEstimate() {
        return restorationEstimate;
    }

    public void setRestorationEstimate(String restorationEstimate) {
        this.restorationEstimate = restorationEstimate;
    }

    public String getAlertsOn() {
        return alertsOn;
    }

    public void setAlertsOn(String alertsOn) {
        this.alertsOn = alertsOn;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getLatestInfo() {
        return latestInfo;
    }

    public void setLatestInfo(String latestInfo) {
        this.latestInfo = latestInfo;
    }

    public String getOutageCause() {
        return outageCause;
    }

    public void setOutageCause(String outageCause) {
        this.outageCause = outageCause;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getNoOfCustomersAffected() {
        return NoOfCustomersAffected;
    }

    public void setNoOfCustomersAffected(String noOfCustomersAffected) {
        NoOfCustomersAffected = noOfCustomersAffected;
    }

    public String getDebrisInfo() {
        return debrisInfo;
    }

    public void setDebrisInfo(String debrisInfo) {
        this.debrisInfo = debrisInfo;
    }

    public String getOtherInstructions() {
        return otherInstructions;
    }

    public void setOtherInstructions(String otherInstructions) {
        this.otherInstructions = otherInstructions;
    }

    public String getAccessGateCode() {
        return accessGateCode;
    }

    public void setAccessGateCode(String accessGateCode) {
        this.accessGateCode = accessGateCode;
    }

    public String getAnyPets() {
        return anyPets;
    }

    public void setAnyPets(String anyPets) {
        this.anyPets = anyPets;
    }
}
