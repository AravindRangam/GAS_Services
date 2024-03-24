package com.kartha.cssp.utils;

import com.kartha.cssp.data.ValidateEmailData;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;

import static java.util.regex.Pattern.compile;

public class CommonUtils {

    public static boolean isValidObject(Object object) {
        return (object != null);
    }

    // Below method validateEmailAddress takes email address as input and
    // returns an array of boolean values which has -
    // Regular expression Failure - True indicates failure
    // IsSmtpFailure - True Indicates domain failure
    // IsMailboxFailure - True Indicates Mailbox validation failure
    public static ValidateEmailData validateEmailAddress(String email) throws IOException {
        // write code here
        System.out.println(email);

        ValidateEmailData validateEmailData = new ValidateEmailData();

        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = compile(regex);

        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            validateEmailData.setRegularExpFailure(false);
            /*
             * try {
             * // Create REST client object with your credentials
             * 
             * VerifaliaRestClient restClient = new VerifaliaRestClient("sumanuser",
             * "sumanpassword@1");
             * 
             * // Submit email verification request with waiting parameters
             * Validation result = restClient.getEmailValidations().submit(new
             * String[]{email},
             * new WaitForCompletionOptions(20) // in seconds
             * );
             * 
             * if (result == null) { // Result is null if timeout expires
             * System.err.println("Request timeout expired");
             * } else {
             * // Display results
             * for (ValidationEntry entry : result.getEntries()) {
             * System.out.printf("Address: %s => Result: %s\n",
             * entry.getInputData(),
             * entry.getStatus());
             * System.out.println("MAILBOX" + entry.getIsMailboxFailure());
             * System.out.println("SMTP" + entry.getIsSmtpFailure());
             * validateEmailData.setMailboxFailure(entry.getIsMailboxFailure());
             * validateEmailData.setSmtpFailure(entry.getIsSmtpFailure());
             * }
             * }
             * } catch (Exception e) {
             * e.printStackTrace();
             * }
             */
            validateEmailData.setMailboxFailure(true);
            validateEmailData.setSmtpFailure(true);
        }
        return validateEmailData;

    }

    public static String getCurrentTimeStamp() {
        // Get current date time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss:SSS");

        return now.format(formatter);
    }

    public static boolean validateLast4Digits(String accountSSN, String userInput) {
        return (userInput.equalsIgnoreCase(accountSSN.substring(accountSSN.length() - 4)));
    }

    public static void convertToNumber() {
        String s = "9876000001";
        long i = Long.parseLong(s);
        System.out.println(new Long(s));

    }

    // add a method to generate random string of input length
    public static String generateRandomString(int length) {
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        // ensure the string has atleast one numeric character
        generatedString = generatedString + randomNumeric();
        return generatedString;
    }

    private static int randomNumeric() {
        Random rand = new Random();
        return rand.nextInt(1000);
    }

}
