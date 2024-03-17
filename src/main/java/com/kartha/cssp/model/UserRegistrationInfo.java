package com.kartha.cssp.model;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@Document(collection = "user_management")
public class UserRegistrationInfo {

    @Id
    private String id;
    private String userId;
    private String key;
    private String password;
    private String userCreatedTS;
    private String userLastUpdatedTS;
    private List<UserAccountInfo> userAccountInfo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserCreatedTS() {
        return userCreatedTS;
    }

    public void setUserCreatedTS(String userCreatedTS) {
        this.userCreatedTS = userCreatedTS;
    }

    public String getUserLastUpdatedTS() {
        return userLastUpdatedTS;
    }

    public void setUserLastUpdatedTS(String userLastUpdatedTS) {
        this.userLastUpdatedTS = userLastUpdatedTS;
    }

    public List<UserAccountInfo> getUserAccountInfo() {
        return userAccountInfo;
    }

    public void setUserAccountInfo(List<UserAccountInfo> userAccountInfo) {
        this.userAccountInfo = userAccountInfo;
    }
}
