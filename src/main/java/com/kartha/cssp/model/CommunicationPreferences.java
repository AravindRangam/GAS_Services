package com.kartha.cssp.model;
import com.kartha.cssp.data.EmailProgramInfo;
import com.kartha.cssp.data.PhoneOptionsTextEnabledOptInData;
import com.kartha.cssp.data.ProgramInfo;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@Document(collection = "communication_preferences")

public class CommunicationPreferences {
    @Id
    private String id;
    private String accountNumber;
    private String preferenceLevel;
    private String companyCode;
    private String lineOfBusiness;
    private List<PhoneOptionsTextEnabledOptInData> phoneOptionsTextEnabledOptIn;
    private List<EmailProgramInfo> emailProgramInfo;

    public List<EmailProgramInfo> getEmailProgramInfo() {
        return emailProgramInfo;
    }

    public void setEmailProgramInfo(List<EmailProgramInfo> emailProgramInfo) {
        this.emailProgramInfo = emailProgramInfo;
    }

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
