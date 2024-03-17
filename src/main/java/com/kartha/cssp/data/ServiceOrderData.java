package com.kartha.cssp.data;

import org.springframework.stereotype.Component;
import java.util.*;
import java.io.Serializable;

@Component
public class ServiceOrderData implements Serializable {
    private String userId;
    private String serviceOrderType;
    private String firstName;
    private String middleName;
    private String lastName;
    private String connectAcccountNumber;
    private String disconnectAccountNumber;
    private String connectDate;
    private String disconnectDate;
    private String ssnTaxidIndicator;
    private String ssnTaxid;
    private String premiseOccupied;
    private String powerOnOFF;
    private String depositAmt;
    private String serviceCharge;
    private String premiseAccessSafe;
    private List<DigitalCommunicationData> digitalCommunicationInfo;
    private List<AddressData> addressData;
    private List<ProgramsData> programsData;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getServiceOrderType() {
        return serviceOrderType;
    }

    public void setServiceOrderType(String serviceOrderType) {
        this.serviceOrderType = serviceOrderType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getConnectAcccountNumber() {
        return connectAcccountNumber;
    }

    public void setConnectAcccountNumber(String connectAcccountNumber) {
        this.connectAcccountNumber = connectAcccountNumber;
    }

    public String getDisconnectAccountNumber() {
        return disconnectAccountNumber;
    }

    public void setDisconnectAccountNumber(String disconnectAccountNumber) {
        this.disconnectAccountNumber = disconnectAccountNumber;
    }

    public String getConnectDate() {
        return connectDate;
    }

    public void setConnectDate(String connectDate) {
        this.connectDate = connectDate;
    }

    public String getDisconnectDate() {
        return disconnectDate;
    }

    public void setDisconnectDate(String disconnectDate) {
        this.disconnectDate = disconnectDate;
    }

    public String getSsnTaxidIndicator() {
        return ssnTaxidIndicator;
    }

    public void setSsnTaxidIndicator(String ssnTaxidIndicator) {
        this.ssnTaxidIndicator = ssnTaxidIndicator;
    }

    public String getSsnTaxid() {
        return ssnTaxid;
    }

    public void setSsnTaxid(String ssnTaxid) {
        this.ssnTaxid = ssnTaxid;
    }

    public String getPremiseOccupied() {
        return premiseOccupied;
    }

    public void setPremiseOccupied(String premiseOccupied) {
        this.premiseOccupied = premiseOccupied;
    }

    public String getPowerOnOFF() {
        return powerOnOFF;
    }

    public void setPowerOnOFF(String powerOnOFF) {
        this.powerOnOFF = powerOnOFF;
    }

    public String getDepositAmt() {
        return depositAmt;
    }

    public void setDepositAmt(String depositAmt) {
        this.depositAmt = depositAmt;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getPremiseAccessSafe() {
        return premiseAccessSafe;
    }

    public void setPremiseAccessSafe(String premiseAccessSafe) {
        this.premiseAccessSafe = premiseAccessSafe;
    }

    public List<DigitalCommunicationData> getDigitalCommunicationInfo() {
        return digitalCommunicationInfo;
    }

    public void setDigitalCommunicationInfo(List<DigitalCommunicationData> digitalCommunicationInfo) {
        this.digitalCommunicationInfo = digitalCommunicationInfo;
    }

    public List<AddressData> getAddressData() {
        return addressData;
    }

    public void setAddressData(List<AddressData> addressData) {
        this.addressData = addressData;
    }

    public List<ProgramsData> getProgramsData() {
        return programsData;
    }

    public void setProgramsData(List<ProgramsData> programsData) {
        this.programsData = programsData;
    }
}
