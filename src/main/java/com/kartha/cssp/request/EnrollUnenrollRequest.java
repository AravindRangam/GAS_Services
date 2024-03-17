package com.kartha.cssp.request;

import org.springframework.stereotype.Component;

@Component
public class EnrollUnenrollRequest extends CSSPServiceRequests {

    private String accountNumber;
    private String enrollUnenrollFlag;
    private String email;
    private boolean ebillEnrolled;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEbillEnrolled() {
        return ebillEnrolled;
    }

    public void setEbillEnrolled(boolean ebillEnrolled) {
        this.ebillEnrolled = ebillEnrolled;
    }
}
