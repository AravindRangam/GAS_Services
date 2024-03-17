package com.kartha.cssp.request;

import org.springframework.stereotype.Component;

@Component
public class ForgotUIDRequest {
    private String accountNumber;
    private String last4SSN;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getLast4SSN() {
        return last4SSN;
    }

    public void setLast4SSN(String last4SSN) {
        this.last4SSN = last4SSN;
    }
}
