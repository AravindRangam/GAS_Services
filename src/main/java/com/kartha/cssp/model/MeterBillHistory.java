package com.kartha.cssp.model;

import com.kartha.cssp.data.EnergyUsageData;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@NoArgsConstructor
@Document(collection = "meter_bill_history")
public class MeterBillHistory {
    @Id
    private String id;
    private String accountNumber;
    private String premiseNumber;
    private String typeOfBusiness;
    private String unitMeasure;

//    private List<MeterReadingBillingData> meterReadingBilling;
    private List<EnergyUsageData> energyUsageData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPremiseNumber() {
        return premiseNumber;
    }

    public void setPremiseNumber(String premiseNumber) {
        this.premiseNumber = premiseNumber;
    }

    public String getTypeOfBusiness() {
        return typeOfBusiness;
    }

    public void setTypeOfBusiness(String typeOfBusiness) {
        this.typeOfBusiness = typeOfBusiness;
    }
//
//    public List<MeterReadingBillingData> getMeterReadingBilling() {
//        return meterReadingBilling;
//    }
//
//    public void setMeterReadingBilling(List<MeterReadingBillingData> meterReadingBilling) {
//        this.meterReadingBilling = meterReadingBilling;
//    }


    public String getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(String unitMeasure) {
        this.unitMeasure = unitMeasure;
    }

    public List<EnergyUsageData> getEnergyUsageData() {
        return energyUsageData;
    }

    public void setEnergyUsageData(List<EnergyUsageData> energyUsageData) {
        this.energyUsageData = energyUsageData;
    }

}