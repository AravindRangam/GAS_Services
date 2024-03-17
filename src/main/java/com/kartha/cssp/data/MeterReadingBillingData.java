package com.kartha.cssp.data;

import org.springframework.stereotype.Component;

@Component
public class MeterReadingBillingData {
    private String meterNo;
    private String contractId;
    private String rateType;
    private String invoice;
    private int rate;
    private int flatRate;
    private int tax;
    private String startDate;
    private int startDateReading;
    private String endDate;
    private int endDateReading;
    private int multiplier;
    private String consumptionUnit;
    private double totalAmount;
    private String dueDate;

    public String getMeterNo() {
        return meterNo;
    }

    public void setMeterNo(String meterNo) {
        this.meterNo = meterNo;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getFlatRate() {
        return flatRate;
    }

    public void setFlatRate(int flatRate) {
        this.flatRate = flatRate;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getStartDateReading() {
        return startDateReading;
    }

    public void setStartDateReading(int startDateReading) {
        this.startDateReading = startDateReading;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getEndDateReading() {
        return endDateReading;
    }

    public void setEndDateReading(int endDateReading) {
        this.endDateReading = endDateReading;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public String getConsumptionUnit() {
        return consumptionUnit;
    }

    public void setConsumptionUnit(String consumptionUnit) {
        this.consumptionUnit = consumptionUnit;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }


}
