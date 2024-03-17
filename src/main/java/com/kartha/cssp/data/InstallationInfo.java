package com.kartha.cssp.data;

import java.io.Serializable;

import java.util.List;

public class InstallationInfo implements Serializable {
    private List<InstallationInfoData> installationInfoData;

    public List<InstallationInfoData> getInstallationInfoData() {
        return installationInfoData;
    }

    public void setInstallationInfoData(List<InstallationInfoData> installationInfoData) {
        this.installationInfoData = installationInfoData;
    }
}
