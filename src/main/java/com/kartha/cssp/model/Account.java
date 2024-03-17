package com.kartha.cssp.model;

import com.kartha.cssp.data.*;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@Document(collection = "account_info")
public class Account {

    @Id
    private String id;
    private String accountNumber;
    private String premiseNumber;
    private String businessPartnerNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String ssnTaxid;
    private String rateCode;
    private List<ContractInfoData> contractInfo;
    private InstallationInfo installationInfo;
    private List<DigitalCommunicationData> digitalCommunicationInfo;
    private List<AddressData> address;

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

    public String getSsnTaxid() {
        return ssnTaxid;
    }

    public void setSsnTaxid(String ssnTaxid) {
        this.ssnTaxid = ssnTaxid;
    }

    public String getRateCode() {
        return rateCode;
    }

    public void setRateCode(String rateCode) {
        this.rateCode = rateCode;
    }

    public List<ContractInfoData> getContractInfo() {
        return contractInfo;
    }

    public void setContractInfo(List<ContractInfoData> contractInfo) {
        this.contractInfo = contractInfo;
    }

    public InstallationInfo getInstallationInfo() {
        return installationInfo;
    }

    public void setInstallationInfo(InstallationInfo installationInfo) {
        this.installationInfo = installationInfo;
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

}
