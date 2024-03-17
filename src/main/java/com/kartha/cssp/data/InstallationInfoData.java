package com.kartha.cssp.data;

import java.io.Serializable;

public class InstallationInfoData implements Serializable {
    private String installationNumber;
    private String installationTypeCode;
    private String installationTypeDesc;
    private Boolean installationTypeFlag;
    private String contractID;

    public String getInstallationNumber() {
        return installationNumber;
    }

    public void setInstallationNumber(String installationNumber) {
        this.installationNumber = installationNumber;
    }

    public String getInstallationTypeCode() {
        return installationTypeCode;
    }

    public void setInstallationTypeCode(String installationTypeCode) {
        this.installationTypeCode = installationTypeCode;
    }

    public String getInstallationTypeDesc() {
        return installationTypeDesc;
    }

    public void setInstallationTypeDesc(String installationTypeDesc) {
        this.installationTypeDesc = installationTypeDesc;
    }

    public Boolean getInstallationTypeFlag() {
        return installationTypeFlag;
    }

    public void setInstallationTypeFlag(Boolean installationTypeFlag) {
        this.installationTypeFlag = installationTypeFlag;
    }

    public String getContractID() {
        return contractID;
    }

    public void setContractID(String contractID) {
        this.contractID = contractID;
    }

}
