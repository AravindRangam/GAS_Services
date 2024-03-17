package com.kartha.cssp.model;


import java.util.List;

public class UserAccountInfo {

    private String accountNumber;
    private String premiseNumber;
    private String businessPartnerNumber;
    private String typeOfBusiness;
    private String nickName;
    private String accountStatus;
    private String rowCreatedTS;
    private String rowUpdatedTS;
    private String rowDeletedInd;
    private String userAccountRelation;
    private String accountAddedFrom;
    private String accountAddedBy;
    private String defaultAccount;
    private String defaultAccountChangeTS;
    private List<AccountPremiseInfo> premiseServiceAddress;

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

    public String getTypeOfBusiness() {
        return typeOfBusiness;
    }

    public void setTypeOfBusiness(String typeOfBusiness) {
        this.typeOfBusiness = typeOfBusiness;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getRowCreatedTS() {
        return rowCreatedTS;
    }

    public void setRowCreatedTS(String rowCreatedTS) {
        this.rowCreatedTS = rowCreatedTS;
    }

    public String getRowUpdatedTS() {
        return rowUpdatedTS;
    }

    public void setRowUpdatedTS(String rowUpdatedTS) {
        this.rowUpdatedTS = rowUpdatedTS;
    }

    public String getRowDeletedInd() {
        return rowDeletedInd;
    }

    public void setRowDeletedInd(String rowDeletedInd) {
        this.rowDeletedInd = rowDeletedInd;
    }

    public String getUserAccountRelation() {
        return userAccountRelation;
    }

    public void setUserAccountRelation(String userAccountRelation) {
        this.userAccountRelation = userAccountRelation;
    }

    public String getAccountAddedFrom() {
        return accountAddedFrom;
    }

    public void setAccountAddedFrom(String accountAddedFrom) {
        this.accountAddedFrom = accountAddedFrom;
    }

    public String getAccountAddedBy() {
        return accountAddedBy;
    }

    public void setAccountAddedBy(String accountAddedBy) {
        this.accountAddedBy = accountAddedBy;
    }

    public String getDefaultAccount() {
        return defaultAccount;
    }

    public void setDefaultAccount(String defaultAccount) {
        this.defaultAccount = defaultAccount;
    }

    public String getDefaultAccountChangeTS() {
        return defaultAccountChangeTS;
    }

    public void setDefaultAccountChangeTS(String defaultAccountChangeTS) {
        this.defaultAccountChangeTS = defaultAccountChangeTS;
    }

    public List<AccountPremiseInfo> getPremiseServiceAddress() {
        return premiseServiceAddress;
    }

    public void setPremiseServiceAddress(List<AccountPremiseInfo> premiseServiceAddress) {
        this.premiseServiceAddress = premiseServiceAddress;
    }
}
