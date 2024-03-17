package com.kartha.cssp.data;

public class DigitalCommunicationData {

    private String communicationType;
    private String priority;
    private String communicateTo;
    private String createdCommTs;
    private String updatedCommTs;

    public String getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(String communicationType) {
        this.communicationType = communicationType;
    }

    public String getPriority() { return priority; }

    public void setPriority(String priority) { this.priority = priority; }

    public String getCommunicateTo() {
        return communicateTo;
    }

    public void setCommunicateTo(String communicateTo) {
        this.communicateTo = communicateTo;
    }

    public String getCreatedCommTs() {
        return createdCommTs;
    }

    public void setCreatedCommTs(String createdCommTs) {
        this.createdCommTs = createdCommTs;
    }

    public String getUpdatedCommTs() {
        return updatedCommTs;
    }

    public void setUpdatedCommTs(String updatedCommTs) {
        this.updatedCommTs = updatedCommTs;
    }
}
