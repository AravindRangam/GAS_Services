package com.kartha.cssp.data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class AccountData implements Serializable {

    private String accountNumber;
    private String premiseNumber;
    private String businessPartnerNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String rateCode;
    private String ssn;
    private boolean embEnrolled;
    private String unitMeasure;
    private CardsLayout customCardsLayout;
    private CardsLayout suggestedCardsLayout;
    private ContractInfoData contractInfoData;
    private InstallationInfo installationInfo;
    private List<DigitalCommunicationData> digitalCommunicationInfo;
    private List<AddressData> address;
    private List<ProgramsData> programsData;
//    private List<MeterReadingBillingData> meterReadingBilling;
    private List<EnergyUsageData> energyUsageData;
    private List<TemperatureData> temperatureDataList;
    private TemperatureData nextBillTemperature;
    private List<OutageInfo> outageInfo;

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

    public String getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(String unitMeasure) {
        this.unitMeasure = unitMeasure;
    }

    public String getBusinessPartnerNumber() {
        return businessPartnerNumber;
    }

    public void setBusinessPartnerNumber(String businessPartnerNumber) {
        this.businessPartnerNumber = businessPartnerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRateCode() {
        return rateCode;
    }

    public void setRateCode(String rateCode) {
        this.rateCode = rateCode;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public boolean isEmbEnrolled() {
        return embEnrolled;
    }

    public void setEmbEnrolled(boolean embEnrolled) {
        this.embEnrolled = embEnrolled;
    }

    public ContractInfoData getContractInfoData() {
        return contractInfoData;
    }

    public void setContractInfoData(ContractInfoData contractInfoData) {
        this.contractInfoData = contractInfoData;
    }

    public List<DigitalCommunicationData> getDigitalCommunicationInfo() {
        return digitalCommunicationInfo;
    }

    public void setDigitalCommunicationInfo(List<DigitalCommunicationData> digitalCommunicationInfo) {
        this.digitalCommunicationInfo = digitalCommunicationInfo;
    }

    public List<AddressData> getAddress() {
        return address;
    }

    public void setAddress(List<AddressData> address) {
        this.address = address;
    }

    public List<ProgramsData> getProgramsData() {
        return programsData;
    }

    public void setProgramsData(List<ProgramsData> programsData) {
        this.programsData = programsData;
    }

//
//    public List<MeterReadingBillingData> getMeterReadingBilling() {
//        return meterReadingBilling;
//    }
//
//    public void setMeterReadingBilling(List<MeterReadingBillingData> meterReadingBilling) {
//        this.meterReadingBilling = meterReadingBilling;
//    }

    public List<EnergyUsageData> getEnergyUsageData() {
        return energyUsageData;
    }

    public void setEnergyUsageData(List<EnergyUsageData> energyUsageData) {
        this.energyUsageData = energyUsageData;
    }


    public List<TemperatureData> getTemperatureDataList() {
        return temperatureDataList;
    }

    public void setTemperatureDataList(List<TemperatureData> temperatureDataList) {
        this.temperatureDataList = temperatureDataList;
    }

    public TemperatureData getNextBillTemperature() {
        return nextBillTemperature;
    }

    public void setNextBillTemperature(TemperatureData nextBillTemperature) {
        this.nextBillTemperature = nextBillTemperature;
    }

    public InstallationInfo getInstallationInfo() {
        return installationInfo;
    }

    public void setInstallationInfo(InstallationInfo installationInfo) {
        this.installationInfo = installationInfo;
    }

    public List<OutageInfo> getOutageInfo() {
        return outageInfo;
    }

    public void setOutageInfo(List<OutageInfo> outageInfo) {
        this.outageInfo = outageInfo;
    }

    public CardsLayout getCustomCardsLayout() {
        return customCardsLayout;
    }

    public void setCustomCardsLayout(CardsLayout customCardsLayout) {
        this.customCardsLayout = customCardsLayout;
    }

    public CardsLayout getSuggestedCardsLayout() {
        return suggestedCardsLayout;
    }

    public void setSuggestedCardsLayout(CardsLayout suggestedCardsLayout) {
        this.suggestedCardsLayout = suggestedCardsLayout;
    }
}
