package com.kartha.cssp.data;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContractAndBillingData {

    private List<MeterReadingBillingData> meterReadingBillingData;

    public List<MeterReadingBillingData> getMeterReadingBillingData() {
        return meterReadingBillingData;
    }

    public void setMeterReadingBillingData(List<MeterReadingBillingData> meterReadingBillingData) {
        this.meterReadingBillingData = meterReadingBillingData;
    }

}

