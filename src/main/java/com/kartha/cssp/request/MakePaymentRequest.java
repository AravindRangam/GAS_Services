package com.kartha.cssp.request;

import org.springframework.stereotype.Component;

@Component
public class MakePaymentRequest {

    private String accountNumber;
    private String amount;
    private String userId;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}

