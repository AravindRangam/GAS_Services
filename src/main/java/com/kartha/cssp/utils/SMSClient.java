package com.kartha.cssp.utils;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;

import java.util.HashMap;
import java.util.Map;

public class SMSClient {

    public static void sendSMS(String mobileNumber) throws Exception {
        //Used for authenticating to AWS
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIA6OQHK4EWWWY23RRZ", "Ifh0yIZxORd0YM7AD9ols9tR5o/IhGtHw6WmPvgO");

        //Create SNS Client
        AmazonSNS snsClient = AmazonSNSClient
                .builder()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

        String SMSMessage = "ProKartha Alert: You have turned on text message alerts. Reply HELP for help or STOP to cancel. Alert frequency varies. Msg & Data rates may apply";

        Map<String, MessageAttributeValue> smsAttributes =
                new HashMap<String, MessageAttributeValue>();

        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
                .withStringValue("Promotional") //Sets the type to promotional.
                .withDataType("String"));

        //PublishResult pr = snsClient.publish(new PublishRequest().withMessage(SMSMessage)
          //                                                          .withPhoneNumber("+17867174792")
          //                                                              .withMessageAttributes(smsAttributes));

        //System.out.println("--------PR----------------"+pr.getSdkResponseMetadata().getRequestId());
    }
}