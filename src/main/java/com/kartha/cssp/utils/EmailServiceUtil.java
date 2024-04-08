package com.kartha.cssp.utils;

import com.kartha.cssp.data.AccountData;
import com.kartha.cssp.data.DigitalCommunicationData;
import com.kartha.cssp.data.ServiceRequestAttachment;
import com.kartha.cssp.data.ServiceRequestData;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.text.html.CSS;

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

    public void sendPasswordResetEmail(String email, String resetPassword) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setFrom("info@iwebtechservices.com");
        msg.setSubject("Your password to ProKartha has been reset!!!");
        String messageBody = "Hi User, \n\nYour password has been reset to: " + resetPassword
                + "\n\nPlease login to the ProKartha and change your password.\n\nThanks,\nProKartha Team";
        msg.setText(messageBody);
        sendEmail(email, msg);
    }

    private void sendEmail(AccountData accountData, String emailTemplate, String email) {
        SimpleMailMessage msg = new SimpleMailMessage();
        // msg.setTo("vizaykris@gmail.com");
        msg.setTo(email);
        msg.setFrom("info@iwebtechservices.com");
        if (emailTemplate.equalsIgnoreCase(CSSPConstants.EMAIL_REGISTRATION)) {
            msg.setSubject("Welcome User");
            msg.setText("Welcome to ProKartha !!! ");
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
        } else if (emailTemplate.equalsIgnoreCase(CSSPConstants.RESET_PASSWORD_SUCCESS)) {
            msg.setSubject("Hello User");
            // can you add a proper message here? with footer and header
            msg.setText("Password reset successful! \n - The ProKartha Team");

        }

        sendEmail(email, msg);
    }

    @SuppressWarnings("null")
    public void sendEmail(String emailAddress, SimpleMailMessage msg) {
        // List of specific email addresses to check
        emailAddress = emailAddress.toLowerCase();
        List<String> specificEmails = Arrays.asList("suman.chimata@gmail.com", "sunil.s@iwebte.com",
                "vizaykris@gmail.com");

        // List of domains to check
        List<String> domains = Arrays.asList("iwebtechservices.com", "iwebte.com");

        // Extract the domain from the email address
        String domain = emailAddress.substring(emailAddress.indexOf("@") + 1);

        // Check if the email address is in the specific list or the domain is in the
        // domain list
        if (specificEmails.contains(emailAddress) || domains.contains(domain)) {
            javaMailSender.send(msg);
        }
    }

    public void sendEmailWithAttachment(String emailAddress, ServiceRequestData data,
            List<ServiceRequestAttachment> attachments) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo("info@iwebte.com");
        helper.setCc("suman.c@iwebte.com");
        // helper.setTo("sunil.s@iwebte.com");
        helper.setFrom(emailAddress);
        helper.setSubject("ProKartha - Service Request Created Successfully");
        helper.setText("Service Request Created Successfully by " + data.getFirstName() + " " + data.getLastName());

        // construct a template based on ServiceRequestData
        StringBuilder sb = new StringBuilder();
        sb.append("Service Request Created Successfully\n\n");
        sb.append("Primary Category: " + data.getPrimaryCategory() + "\n");
        sb.append("Secondary Category: " + data.getSecondaryCategory() + "\n");
        sb.append("First Name: " + data.getFirstName() + "\n");
        sb.append("Last Name: " + data.getLastName() + "\n");
        sb.append("Email: " + data.getEmail() + "\n");
        sb.append("Phone Number: " + data.getPhoneNumber() + "\n");
        sb.append("Account Number: " + data.getAccountNumber() + "\n");
        sb.append("Street Address: " + data.getStreetAddress() + "\n");
        sb.append("City: " + data.getCity() + "\n");
        sb.append("State: " + data.getState() + "\n");
        sb.append("Zip Code: " + data.getZipCode() + "\n");
        sb.append("Message: " + data.getMessage() + "\n");

        helper.setText(sb.toString());

        // add attachments
        for (ServiceRequestAttachment attachment : attachments) {
            byte[] doc = Base64.getMimeDecoder().decode(attachment.getEncodedData());
            helper.addAttachment(attachment.getName(), new ByteArrayResource(doc));
        }

        javaMailSender.send(message);
    }

}
