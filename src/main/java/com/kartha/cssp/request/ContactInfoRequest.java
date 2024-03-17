package com.kartha.cssp.request;

import com.kartha.cssp.data.DigitalCommunicationData;

import java.util.List;

public class ContactInfoRequest extends CSSPServiceRequests {

    private String accountNumber;
    private List<DigitalCommunicationData> data;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<DigitalCommunicationData> getData() {
        return data;
    }

    public void setData(List<DigitalCommunicationData> data) {
        this.data = data;
    }
}
