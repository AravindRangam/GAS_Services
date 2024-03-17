package com.kartha.cssp.request;

import org.springframework.stereotype.Component;

@Component
public class CreateUserRequest
        extends CSSPServiceRequests {

    private String accountNumber;
    private String ssn;
    private String accountEmailAddress;
    private String registeredPassword;
    private String confirmPassword;
    private boolean embEnrolled;
    private String userId;
    private String addedFrom;
    private boolean autoRegFlag;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getAccountEmailAddress() {
        return accountEmailAddress;
    }

    public void setAccountEmailAddress(String accountEmailAddress) {
        this.accountEmailAddress = accountEmailAddress;
    }

    public String getRegisteredPassword() {
        return registeredPassword;
    }

    public void setRegisteredPassword(String registeredPassword) {
        this.registeredPassword = registeredPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isEmbEnrolled() {
        return embEnrolled;
    }

    public void setEmbEnrolled(boolean embEnrolled) {
        this.embEnrolled = embEnrolled;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddedFrom() {
        return addedFrom;
    }

    public void setAddedFrom(String addedFrom) {
        this.addedFrom = addedFrom;
    }

    public boolean isAutoRegFlag() {
        return autoRegFlag;
    }

    public void setAutoRegFlag(boolean autoRegFlag) {
        this.autoRegFlag = autoRegFlag;
    }
}
