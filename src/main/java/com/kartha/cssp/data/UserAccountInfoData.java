package com.kartha.cssp.data;

public class UserAccountInfoData {
    private String accountNumber;
    private String nickName;
    private String rowCreatedTs;
    private String rowUpdatedTS;
    private String rowDeletedInd;
    private String userAccountRelation;
    private String accountAddedFrom;
    private String accountAddedBy;
    private String defaultAccount;
    private String defaultAccountChangeTS;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRowCreatedTs() {
        return rowCreatedTs;
    }

    public void setRowCreatedTs(String rowCreatedTs) {
        this.rowCreatedTs = rowCreatedTs;
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

}
