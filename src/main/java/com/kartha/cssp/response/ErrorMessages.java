package com.kartha.cssp.response;

public class ErrorMessages {

    private String requestStatus;
    private String resultCode;
    private String resultMessage;

    public ErrorMessages(String resultCode, String requestStatus, String resultMessage) {
        this.resultCode = resultCode;
        this.requestStatus = requestStatus;
        this.resultMessage = resultMessage;
    }

    public ErrorMessages(String resultCode, String requestStatus) {
        this.resultCode = resultCode;
        this.requestStatus = requestStatus;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
