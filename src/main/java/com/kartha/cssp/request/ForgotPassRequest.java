package com.kartha.cssp.request;

import org.springframework.stereotype.Component;

@Component
public class ForgotPassRequest {

    private String userId;
    private String last4SSN;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLast4SSN() {
        return last4SSN;
    }

    public void setLast4SSN(String last4SSN) {
        this.last4SSN = last4SSN;
    }

}
