package com.kartha.cssp.request;

import org.springframework.stereotype.Component;

@Component
public class SendEmailRequest {

    private String emailId;
    private String ccEmailId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mailingAddress;
    private String accountNumber;
    private String userId;
    private String emailSubject;
    private String emailBody;
    private String emailType;
    private String resetCodeOrURL;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCcEmailId() {
        return ccEmailId;
    }

    public void setCcEmailId(String ccEmailId) {
        this.ccEmailId = ccEmailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public String getResetCodeOrURL() {
        return resetCodeOrURL;
    }

    public void setResetCodeOrURL(String resetCodeOrURL) {
        this.resetCodeOrURL = resetCodeOrURL;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

}

