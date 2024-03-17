package com.kartha.cssp.data;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class EnergyUsageData {

    private String documentNumber;
    private String toDate;
    private String fromDate;
    private String billedConsUnits;
    private String billedConsAmt;
    private String serviceDays;
    private String avgDailyUnits;
    private String avgDailyAmt;
    private String diffConsUnits;
    private String diffConsAmt;
    private String diffServiceDays;
    private String contractType;
    private String baseCharges;
    List<UsageByContract> usageByContract;
    List<DailyUsageData> dailyUsage;

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
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

    public String getServiceDays() {
        return serviceDays;
    }

    public void setServiceDays(String serviceDays) {
        this.serviceDays = serviceDays;
    }

    public String getAvgDailyUnits() {
        return avgDailyUnits;
    }

    public void setAvgDailyUnits(String avgDailyUnits) {
        this.avgDailyUnits = avgDailyUnits;
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

    public String getDiffServiceDays() {
        return diffServiceDays;
    }

    public void setDiffServiceDays(String diffServiceDays) {
        this.diffServiceDays = diffServiceDays;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getBaseCharges() {
        return baseCharges;
    }

    public void setBaseCharges(String baseCharges) {
        this.baseCharges = baseCharges;
    }

    public List<UsageByContract> getUsageByContract() {
        return usageByContract;
    }

    public void setUsageByContract(List<UsageByContract> usageByContract) {
        this.usageByContract = usageByContract;
    }

    public List<DailyUsageData> getDailyUsage() {
        return dailyUsage;
    }

    public void setDailyUsage(List<DailyUsageData> dailyUsage) {
        this.dailyUsage = dailyUsage;
    }

}
