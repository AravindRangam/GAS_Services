package com.kartha.cssp.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AccountSSNData {

    @NotBlank
    @Size(min = 5, max = 10)
    private String accountNumber;

    @NotBlank
    @Size(min=4, max=4)
    private String ssn;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
