package com.kartha.cssp.request;

import com.kartha.cssp.data.BankData;

public class BankDetailsRequest extends  CSSPServiceRequests{

    private String accountNumber;
    private String enrollUnenrollFlag;
    private BankData data;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public String getEnrollUnenrollFlag() {
        return enrollUnenrollFlag;
    }

    public void setEnrollUnenrollFlag(String enrollUnenrollFlag) {
        this.enrollUnenrollFlag = enrollUnenrollFlag;
    }

    public BankData getData() {
        return data;
    }

    public void setData(BankData data) {
        this.data = data;
    }

}

