package com.kartha.cssp.data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class AccountEmailData implements Serializable {

    private String accountNumber;
    private List<DigitalCommunicationData> digitalCommunicationData;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<DigitalCommunicationData> getDigitalCommunicationData() {
        return digitalCommunicationData;
    }

    public void setDigitalCommunicationData(List<DigitalCommunicationData> digitalCommunicationData) {
        this.digitalCommunicationData = digitalCommunicationData;
    }

}
