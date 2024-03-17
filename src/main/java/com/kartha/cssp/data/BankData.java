package com.kartha.cssp.data;

import org.springframework.stereotype.Component;

@Component
public class BankData {

    private String paymentType;
    private Integer payBeforeDueDate;
    private String bankAccountDescription;
    private String accountHolderFirstName;
    private String accountHolderLastName;
    private String checkingOrSavings;
    private String accountType;
    private String routingTransitNo;
    private String bankAccountNo;


    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getPayBeforeDueDate() {
        return payBeforeDueDate;
    }

    public void setPayBeforeDueDate(Integer payBeforeDueDate) {
        this.payBeforeDueDate = payBeforeDueDate;
    }

    public String getBankAccountDescription() {
        return bankAccountDescription;
    }

    public void setBankAccountDescription(String bankAccountDescription) {
        this.bankAccountDescription = bankAccountDescription;
    }

    public String getAccountHolderFirstName() {
        return accountHolderFirstName;
    }

    public void setAccountHolderFirstName(String accountHolderFirstName) {
        this.accountHolderFirstName = accountHolderFirstName;
    }

    public String getAccountHolderLastName() {
        return accountHolderLastName;
    }

    public void setAccountHolderLastName(String accountHolderLastName) {
        this.accountHolderLastName = accountHolderLastName;
    }

    public String getCheckingOrSavings() {
        return checkingOrSavings;
    }

    public void setCheckingOrSavings(String checkingOrSavings) {
        this.checkingOrSavings = checkingOrSavings;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getRoutingTransitNo() {
        return routingTransitNo;
    }

    public void setRoutingTransitNo(String routingTransitNo) {
        this.routingTransitNo = routingTransitNo;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

}
