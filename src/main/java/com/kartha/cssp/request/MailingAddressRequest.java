package com.kartha.cssp.request;

import com.kartha.cssp.data.AddressData;

public class MailingAddressRequest extends CSSPServiceRequests {

    private String accountNumber;

    private AddressData data;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AddressData getData() {
        return data;
    }

    public void setData(AddressData data) {
        this.data = data;
    }

}
