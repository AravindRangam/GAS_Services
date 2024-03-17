package com.kartha.cssp.data;

import org.springframework.stereotype.Component;

@Component
public class ValidateEmailData {
    boolean regularExpFailure = true;
    boolean smtpFailure = true;

    public boolean isRegularExpFailure() {
        return regularExpFailure;
    }

    public void setRegularExpFailure(boolean regularExpFailure) {
        this.regularExpFailure = regularExpFailure;
    }

    public boolean isSmtpFailure() {
        return smtpFailure;
    }

    public void setSmtpFailure(boolean smtpFailure) {
        this.smtpFailure = smtpFailure;
    }

    public boolean isMailboxFailure() {
        return mailboxFailure;
    }

    public void setMailboxFailure(boolean mailboxFailure) {
        this.mailboxFailure = mailboxFailure;
    }

    boolean mailboxFailure = true;

}
