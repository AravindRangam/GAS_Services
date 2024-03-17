package com.kartha.cssp.exception;

public class CSSPServiceException extends RuntimeException {

    private String errorMessage;
    private String errorCode;

    public CSSPServiceException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    public CSSPServiceException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
