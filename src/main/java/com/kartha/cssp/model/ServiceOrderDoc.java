package com.kartha.cssp.model;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Document(collection = "service_orders")
public class ServiceOrderDoc {
    @Id
    private String id;
    private String userId;
    private String serviceOrderType;
    private String serviceOrderNumber;
    private String connectAcccountNumber;
    private String disconnectAccountNumber;
    private String connectDate;
    public String getDisconnectDate() {
        return disconnectDate;
    }

    private String disconnectDate;
    private String createTimestamp;
    private String updateTimestamp;

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

    public String getServiceOrderType() {
        return serviceOrderType;
    }

    public void setServiceOrderType(String serviceOrderType) {
        this.serviceOrderType = serviceOrderType;
    }

    public String getServiceOrderNumber() {
        return serviceOrderNumber;
    }

    public void setServiceOrderNumber(String serviceOrderNumber) {
        this.serviceOrderNumber = serviceOrderNumber;
    }

    public String getConnectAcccountNumber() {
        return connectAcccountNumber;
    }

    public void setConnectAcccountNumber(String connectAcccountNumber) {
        this.connectAcccountNumber = connectAcccountNumber;
    }

    public String getDisconnectAccountNumber() {
        return disconnectAccountNumber;
    }

    public void setDisconnectDate(String disconnectDate) {
        this.disconnectDate = disconnectDate;
    }

    public void setDisconnectAccountNumber(String disconnectAccountNumber) {
        this.disconnectAccountNumber = disconnectAccountNumber;
    }

    public String getConnectDate() {
        return connectDate;
    }

    public void setConnectDate(String connectDate) {
        this.connectDate = connectDate;
    }


    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

}
