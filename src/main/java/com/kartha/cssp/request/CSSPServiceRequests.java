package com.kartha.cssp.request;

import java.io.Serializable;

public class CSSPServiceRequests implements Serializable {

    private String channel;
    private String requestType;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
