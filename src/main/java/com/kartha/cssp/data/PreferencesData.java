package com.kartha.cssp.data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class PreferencesData implements Serializable {

    private String accountNumber;
    private String preferenceLevel;
    private String companyCode;
    private String lineOfBusiness;
    private List<PhoneOptionsTextEnabledOptInData> phoneOptionsTextEnabledOptIn;
    private List<EmailProgramInfo> emailProgramInfo;


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPreferenceLevel() {
        return preferenceLevel;
    }

    public void setPreferenceLevel(String preferenceLevel) {
        this.preferenceLevel = preferenceLevel;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    public List<PhoneOptionsTextEnabledOptInData> getPhoneOptionsTextEnabledOptIn() {
        return phoneOptionsTextEnabledOptIn;
    }

    public void setPhoneOptionsTextEnabledOptIn(List<PhoneOptionsTextEnabledOptInData> phoneOptionsTextEnabledOptIn) {
        this.phoneOptionsTextEnabledOptIn = phoneOptionsTextEnabledOptIn;
    }

    public List<EmailProgramInfo> getEmailProgramInfo() {
        return emailProgramInfo;
    }

    public void setEmailProgramInfo(List<EmailProgramInfo> emailProgramInfo) {
        this.emailProgramInfo = emailProgramInfo;
    }

}
