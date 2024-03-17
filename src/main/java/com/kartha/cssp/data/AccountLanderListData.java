package com.kartha.cssp.data;

import java.io.Serializable;

public class AccountLanderListData implements Serializable{
    private String accountNumber;
    private String defaultAccount;
    private String accountBalance;
    private String accountStatus;
    private boolean pinVerificationRequired;
    private String serviceAddressLine1;
    private String serviceAddressLine2;
    private String serviceAddressLine3;
    private String wholeName;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDefaultAccount() {
        return defaultAccount;
    }

    public void setDefaultAccount(String defaultAccount) {
        this.defaultAccount = defaultAccount;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public boolean isPinVerificationRequired() {
        return pinVerificationRequired;
    }

    public void setPinVerificationRequired(boolean pinVerificationRequired) {
        this.pinVerificationRequired = pinVerificationRequired;
    }

    public String getServiceAddressLine1() {
        return serviceAddressLine1;
    }

    public void setServiceAddressLine1(String serviceAddressLine1) {
        this.serviceAddressLine1 = serviceAddressLine1;
    }

    public String getServiceAddressLine2() {
        return serviceAddressLine2;
    }

    public void setServiceAddressLine2(String serviceAddressLine2) {
        this.serviceAddressLine2 = serviceAddressLine2;
    }

    public String getServiceAddressLine3() {
        return serviceAddressLine3;
    }

    public void setServiceAddressLine3(String serviceAddressLine3) {
        this.serviceAddressLine3 = serviceAddressLine3;
    }

    public String getWholeName() {
        return wholeName;
    }

    public void setWholeName(String wholeName) {
        this.wholeName = wholeName;
    }
}
