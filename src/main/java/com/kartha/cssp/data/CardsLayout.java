package com.kartha.cssp.data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CardsLayout implements Serializable {

    private int accountOverviewSeqNo;
    private boolean accountOverviewFlipAssist;
    private int accountInfoSeqNo;
    private boolean accountInfoFlipAssist;
    private int billComparisionSeqNo;
    private boolean billComparisionFlipAssist;
    private int billingProgramsSeqNo;
    private boolean billingProgramsFlipAssist;
    private int usageComparisionSeqNo;
    private boolean usageComparisionFlipAssist;
    private int projectedBillingSeqNo;
    private boolean projectedBillingFlipAssist;

    public int getAccountOverviewSeqNo() {
        return accountOverviewSeqNo;
    }

    public void setAccountOverviewSeqNo(int accountOverviewSeqNo) {
        this.accountOverviewSeqNo = accountOverviewSeqNo;
    }

    public boolean isAccountOverviewFlipAssist() {
        return accountOverviewFlipAssist;
    }

    public void setAccountOverviewFlipAssist(boolean accountOverviewFlipAssist) {
        this.accountOverviewFlipAssist = accountOverviewFlipAssist;
    }

    public int getAccountInfoSeqNo() {
        return accountInfoSeqNo;
    }

    public void setAccountInfoSeqNo(int accountInfoSeqNo) {
        this.accountInfoSeqNo = accountInfoSeqNo;
    }

    public boolean isAccountInfoFlipAssist() {
        return accountInfoFlipAssist;
    }

    public void setAccountInfoFlipAssist(boolean accountInfoFlipAssist) {
        this.accountInfoFlipAssist = accountInfoFlipAssist;
    }

    public int getBillComparisionSeqNo() {
        return billComparisionSeqNo;
    }

    public void setBillComparisionSeqNo(int billComparisionSeqNo) {
        this.billComparisionSeqNo = billComparisionSeqNo;
    }

    public boolean isBillComparisionFlipAssist() {
        return billComparisionFlipAssist;
    }

    public void setBillComparisionFlipAssist(boolean billComparisionFlipAssist) {
        this.billComparisionFlipAssist = billComparisionFlipAssist;
    }

    public int getBillingProgramsSeqNo() {
        return billingProgramsSeqNo;
    }

    public void setBillingProgramsSeqNo(int billingProgramsSeqNo) {
        this.billingProgramsSeqNo = billingProgramsSeqNo;
    }

    public boolean isBillingProgramsFlipAssist() {
        return billingProgramsFlipAssist;
    }

    public void setBillingProgramsFlipAssist(boolean billingProgramsFlipAssist) {
        this.billingProgramsFlipAssist = billingProgramsFlipAssist;
    }

    public int getUsageComparisionSeqNo() {
        return usageComparisionSeqNo;
    }

    public void setUsageComparisionSeqNo(int usageComparisionSeqNo) {
        this.usageComparisionSeqNo = usageComparisionSeqNo;
    }

    public boolean isUsageComparisionFlipAssist() {
        return usageComparisionFlipAssist;
    }

    public void setUsageComparisionFlipAssist(boolean usageComparisionFlipAssist) {
        this.usageComparisionFlipAssist = usageComparisionFlipAssist;
    }

    public int getProjectedBillingSeqNo() {
        return projectedBillingSeqNo;
    }

    public void setProjectedBillingSeqNo(int projectedBillingSeqNo) {
        this.projectedBillingSeqNo = projectedBillingSeqNo;
    }

    public boolean isProjectedBillingFlipAssist() {
        return projectedBillingFlipAssist;
    }

    public void setProjectedBillingFlipAssist(boolean projectedBillingFlipAssist) {
        this.projectedBillingFlipAssist = projectedBillingFlipAssist;
    }


}
