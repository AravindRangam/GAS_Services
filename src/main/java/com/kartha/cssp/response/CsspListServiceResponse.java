package com.kartha.cssp.response;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CsspListServiceResponse<T> {

    private List<T> data;
    private Messages message;
    private ErrorMessages errorMessage;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
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
