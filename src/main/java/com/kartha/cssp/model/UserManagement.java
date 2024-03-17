package com.kartha.cssp.model;
import com.kartha.cssp.data.UserAccountInfoData;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@Document(collection = "user_management")
public class UserManagement {
    @Id
    private String id;
    private String userId;
    private String uuid;
    private String defaultAccountNumber;
    private String userCreatedTS;
    private String userLastUpdatedTS;
    private List<UserAccountInfoData> userAccountInfo;
    private String userRole;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDefaultAccountNumber() {
        return defaultAccountNumber;
    }

    public void setDefaultAccountNumber(String defaultAccountNumber) {
        this.defaultAccountNumber = defaultAccountNumber;
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

    public List<UserAccountInfoData> getUserAccountInfo() {
        return userAccountInfo;
    }

    public void setUserAccountInfo(List<UserAccountInfoData> userAccountInfo) {
        this.userAccountInfo = userAccountInfo;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

}
