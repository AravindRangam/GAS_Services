package com.kartha.cssp.data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class UserIdAndBase64 implements Serializable {

    private String userIdMasked;
    private String userIdBase64;

    public String getUserIdMasked() {
        return userIdMasked;
    }

    public void setUserIdMasked(String userIdMasked) {
        this.userIdMasked = userIdMasked;
    }

    public String getUserIdBase64() {
        return userIdBase64;
    }

    public void setUserIdBase64(String userIdBase64) {
        this.userIdBase64 = userIdBase64;
    }
}
