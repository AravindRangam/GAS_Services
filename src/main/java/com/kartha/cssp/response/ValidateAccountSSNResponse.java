package com.kartha.cssp.response;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ValidateAccountSSNResponse implements Serializable {

    private boolean validateEmail;
    private boolean validAccountSSN;
    private boolean securityEnabled;
    private String accountNum;
    private String ssn;
    private String serviceAddress;
    private String email;
    private String ebillStatus;
    private boolean ebillEligible;
    private boolean pinVerificationRequired;

    public boolean isValidateEmail() {
        return validateEmail;
    }

    public void setValidateEmail(boolean validateEmail) {
        this.validateEmail = validateEmail;
    }

    public boolean isValidAccountSSN() {
        return validAccountSSN;
    }

    public void setValidAccountSSN(boolean validAccountSSN) {
        this.validAccountSSN = validAccountSSN;
    }

    public boolean isSecurityEnabled() {
        return securityEnabled;
    }

    public void setSecurityEnabled(boolean securityEnabled) {
        this.securityEnabled = securityEnabled;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEbillStatus() {
        return ebillStatus;
    }

    public void setEbillStatus(String ebillStatus) {
        this.ebillStatus = ebillStatus;
    }

    public boolean isEbillEligible() {
        return ebillEligible;
    }

    public void setEbillEligible(boolean ebillEligible) {
        this.ebillEligible = ebillEligible;
    }

    public boolean isPinVerificationRequired() {
        return pinVerificationRequired;
    }

    public void setPinVerificationRequired(boolean pinVerificationRequired) {
        this.pinVerificationRequired = pinVerificationRequired;
    }
}
