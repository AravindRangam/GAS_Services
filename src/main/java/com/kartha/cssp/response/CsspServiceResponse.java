package com.kartha.cssp.response;

import org.springframework.stereotype.Component;


@Component
public class CsspServiceResponse<T> {

    private T data;
    private Messages message;
    private ErrorMessages errorMessage;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Messages getMessage() {
        return message;
    }

    public void setMessage(Messages message) {
        this.message = message;
    }

    public ErrorMessages getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessages errorMessage) {
        this.errorMessage = errorMessage;
    }
}
