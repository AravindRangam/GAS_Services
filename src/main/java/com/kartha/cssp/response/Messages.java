package com.kartha.cssp.response;

public class Messages {

    private String messageCode;
    private String messageStatus;
    private String message;

    public Messages(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Messages(String messageCode, String messageStatus) {
        this.messageCode = messageCode;
        this.messageStatus = messageStatus;
    }

    public Messages(String messageCode, String messageStatus, String message) {
        this.messageCode = messageCode;
        this.messageStatus = messageStatus;
        this.message = message;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
