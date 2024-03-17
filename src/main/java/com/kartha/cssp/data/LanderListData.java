package com.kartha.cssp.data;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class LanderListData {

    private String userId;
    private String defaultAccountNumber;
    private List<AccountData> accountData;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDefaultAccountNumber() {
        return defaultAccountNumber;
    }

    public void setDefaultAccountNumber(String defaultAccountNumber) {
        this.defaultAccountNumber = defaultAccountNumber;
    }

    public List<AccountData> getAccountData() {
        return accountData;
    }

    public void setAccountData(List<AccountData> accountData) {
        this.accountData = accountData;
    }

}
