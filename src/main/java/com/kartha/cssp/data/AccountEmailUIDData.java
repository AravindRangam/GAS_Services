package com.kartha.cssp.data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class AccountEmailUIDData implements Serializable {

    private String accountNumber;
    private List<DigitalCommunicationData> digitalCommunicationData;
    private List<UserIdAndBase64> userIdAndBase64;

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

    public List<UserIdAndBase64> getUserIdAndBase64() {
        return userIdAndBase64;
    }

    public void setUserIdAndBase64(List<UserIdAndBase64> userIdAndBase64) {
        this.userIdAndBase64 = userIdAndBase64;
    }


}
