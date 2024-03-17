package com.kartha.cssp.request;

import org.springframework.stereotype.Component;

@Component
public class  OutageServiceRequest extends CSSPServiceRequests {

    private String accountNumber;
    private String premiseNumber;
    private String serviceAddress;
    private String buildingNumber;
    private String apartmentNumber;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPremiseNumber() {
        return premiseNumber;
    }

    public void setPremiseNumber(String premiseNumber) {
        this.premiseNumber = premiseNumber;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

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
