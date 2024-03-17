package com.kartha.cssp.request;

import com.kartha.cssp.data.AccountSSNData;

public class ValidateAccountSSNRequest extends CSSPServiceRequests {

    private String userId;
    private AccountSSNData data;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AccountSSNData getData() {
        return data;
    }

    public void setData(AccountSSNData data) {
        this.data = data;
    }
}
