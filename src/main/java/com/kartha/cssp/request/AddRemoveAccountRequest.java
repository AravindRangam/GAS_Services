package com.kartha.cssp.request;

import org.springframework.stereotype.Component;

@Component
public class AddRemoveAccountRequest {
    private String accountNumber;
    private String uuid;
    private String userId;
    private String addDeleteIndicator;
    private String setDefault;
    private String nickName;
    private String addedFrom;
    private String ebillEnroll;


    private String emailId;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddDeleteIndicator() {
        return addDeleteIndicator;
    }

    public void setAddDeleteIndicator(String addDeleteIndicator) {
        this.addDeleteIndicator = addDeleteIndicator;
    }

    public String getSetDefault() {
        return setDefault;
    }

    public void setSetDefault(String setDefault) {
        this.setDefault = setDefault;
    }
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getAddedFrom() {
        return addedFrom;
    }

    public void setAddedFrom(String addedFrom) {
        this.addedFrom = addedFrom;
    }

    public String getEbillEnroll() {
        return ebillEnroll;
    }

    public void setEbillEnroll(String ebillEnroll) {
        this.ebillEnroll = ebillEnroll;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

}
