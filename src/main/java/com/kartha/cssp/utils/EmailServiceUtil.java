package com.kartha.cssp.utils;

import com.kartha.cssp.data.AccountData;
import com.kartha.cssp.data.DigitalCommunicationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class EmailServiceUtil {

    JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailRequest(AccountData accountData,
            String emailTemplate) {
        final String toEmailAddress = "";
        try {
            // Check Email id exists
            List<DigitalCommunicationData> digitalCommunicationDataList = accountData.getDigitalCommunicationInfo();
            Predicate<DigitalCommunicationData> p = digitalCommunicationData -> digitalCommunicationData
                    .getCommunicationType().equalsIgnoreCase("email_id");
            DigitalCommunicationData digitalCommunicationData = digitalCommunicationDataList.stream().filter(p)
                    .collect(Collectors.toList()).get(0);
            System.out.println(
                    "-------digitalCommunicationData email------------" + digitalCommunicationData.getCommunicateTo());
            if (Objects.nonNull(digitalCommunicationData)) {
                sendEmail(accountData, emailTemplate, digitalCommunicationData.getCommunicateTo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendEmailRequest(String email, String emailTemplate) {
        sendEmail(null, emailTemplate, email);
    }

    private void sendEmail(AccountData accountData, String emailTemplate, String email) {
        SimpleMailMessage msg = new SimpleMailMessage();
        // msg.setTo("vizaykris@gmail.com");
        msg.setTo(email);
        // msg.setFrom("info@iwebtechservices.com");
        if (emailTemplate.equalsIgnoreCase(CSSPConstants.EMAIL_REGISTRATION)) {
            msg.setSubject("Welcome User");
            msg.setText("Welcome to SSP Portal !!! ");
        } else if (emailTemplate.equalsIgnoreCase(CSSPConstants.EMAIL_UPDATE_PHONE_NUMBER)) {
            msg.setSubject("Hello User");
            msg.setText("Phone Number Updated !!! ");
        } else if (emailTemplate.equalsIgnoreCase(CSSPConstants.EMAIL_UPDATE_EMAIL_NUMBER)) {
            msg.setSubject("Hello User");
            msg.setText("Email Updated !!! ");
        } else if (emailTemplate.equalsIgnoreCase(CSSPConstants.EMAIL_UPDATE_MAILING_ADDRESS)) {
            msg.setSubject("Hello User");
            msg.setText("Mailing Address Updated !!! ");
        } else if (emailTemplate.equalsIgnoreCase(CSSPConstants.EMAIL_UPDATED_PREFERENCES)) {
            msg.setSubject("Hello User");
            msg.setText("Preferences Updated !!! ");
        } else if (emailTemplate.equalsIgnoreCase(CSSPConstants.EMAIL_MAKE_PAYMENT)) {
            msg.setSubject("Hello User");
            msg.setText("Payment posted !!! ");
        } else if (emailTemplate.equalsIgnoreCase(CSSPConstants.EMAIL_ENROLL_EMB)) {
            msg.setSubject("Hello User");
            msg.setText("EMB Enroll completed !!! ");
        } else if (emailTemplate.equalsIgnoreCase(CSSPConstants.EMAIL_ENROLL_BB)) {
            msg.setSubject("Hello User");
            msg.setText("BB Enroll completed !!! ");
        } else if (emailTemplate.equalsIgnoreCase(CSSPConstants.EMAIL_ENROLL_FLAT_BILL)) {
            msg.setSubject("Hello User");
            msg.setText("Flat Bill enrollment completed !!! ");
        } else if (emailTemplate.equalsIgnoreCase(CSSPConstants.EMAIL_ENROLL_AUTO_PAY)) {
            msg.setSubject("Hello User");
            msg.setText("Auto Pay Enrollment completed !!! ");
        } else if (emailTemplate.equalsIgnoreCase(CSSPConstants.EMAIL_ENROLL_PAY_EXTEND)) {
            msg.setSubject("Hello User");
            msg.setText("Payment extension request completed !!! ");
        } else if (emailTemplate.equalsIgnoreCase(CSSPConstants.EMAIL_ENROLL_ONLINE_PAYMENT)) {
            msg.setSubject("Hello User");
            msg.setText("Online Payment enrollment completed !!! ");
        }

        javaMailSender.send(msg);
    }

}
