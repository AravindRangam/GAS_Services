package com.kartha.cssp.data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class UsageByContract implements Serializable {

    private String contractID;
    private String installationType;
    private String billedConsUnits;
    private String billedConsAmt;
    private String avgDailyUnits;

    private String avgDailyAmt;
    private String diffConsUnits;
    private String diffConsAmt;

    public String getContractID() {
        return contractID;
    }

    public void setContractID(String contractID) {
        this.contractID = contractID;
    }

    public String getInstallationType() {
        return installationType;
    }

    public void setInstallationType(String installationType) {
        this.installationType = installationType;
    }

    public String getBilledConsUnits() {
        return billedConsUnits;
    }

    public void setBilledConsUnits(String billedConsUnits) {
        this.billedConsUnits = billedConsUnits;
    }

    public String getBilledConsAmt() {
        return billedConsAmt;
    }

    public void setBilledConsAmt(String billedConsAmt) {
        this.billedConsAmt = billedConsAmt;
    }

    public String getAvgDailyAmt() {
        return avgDailyAmt;
    }

    public void setAvgDailyAmt(String avgDailyAmt) {
        this.avgDailyAmt = avgDailyAmt;
    }

    public String getDiffConsUnits() {
        return diffConsUnits;
    }

    public void setDiffConsUnits(String diffConsUnits) {
        this.diffConsUnits = diffConsUnits;
    }

    public String getDiffConsAmt() {
        return diffConsAmt;
    }

    public void setDiffConsAmt(String diffConsAmt) {
        this.diffConsAmt = diffConsAmt;
    }

    public String getAvgDailyUnits() {
        return avgDailyUnits;
    }

    public void setAvgDailyUnits(String avgDailyUnits) {
        this.avgDailyUnits = avgDailyUnits;
    }

}
