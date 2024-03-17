package com.kartha.cssp.response;

import com.kartha.cssp.data.AccountLanderListData;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class AccountLanderResponse implements Serializable {

    private String code;
    private String defaultAccount;
    private List<AccountLanderListData> accounts;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDefaultAccount() {
        return defaultAccount;
    }

    public void setDefaultAccount(String defaultAccount) {
        this.defaultAccount = defaultAccount;
    }

    public List<AccountLanderListData> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountLanderListData> accounts) {
        this.accounts = accounts;
    }
}
