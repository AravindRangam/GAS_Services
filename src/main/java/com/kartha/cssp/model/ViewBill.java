package com.kartha.cssp.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Document(collection = "view_bill")
public class ViewBill {

    @Id
    public String id;
    public String accountNumber;
    public String lastBill;
    public String currentBillDate;
    public String billStartDate;
    public String billEndDate;
    public String lastPayment;
    public String balancePriorNewBill;
    public ArrayList<NewCharge> newCharges;

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

    public String getLastBill() {
        return lastBill;
    }

    public void setLastBill(String lastBill) {
        this.lastBill = lastBill;
    }

    public String getCurrentBillDate() {
        return currentBillDate;
    }

    public void setCurrentBillDate(String currentBillDate) {
        this.currentBillDate = currentBillDate;
    }

    public String getBillStartDate() {
        return billStartDate;
    }

    public void setBillStartDate(String billStartDate) {
        this.billStartDate = billStartDate;
    }

    public String getBillEndDate() {
        return billEndDate;
    }

    public void setBillEndDate(String billEndDate) {
        this.billEndDate = billEndDate;
    }

    public String getLastPayment() {
        return lastPayment;
    }

    public void setLastPayment(String lastPayment) {
        this.lastPayment = lastPayment;
    }

    public String getBalancePriorNewBill() {
        return balancePriorNewBill;
    }

    public void setBalancePriorNewBill(String balancePriorNewBill) {
        this.balancePriorNewBill = balancePriorNewBill;
    }

    public ArrayList<NewCharge> getNewCharges() {
        return newCharges;
    }

    public void setNewCharges(ArrayList<NewCharge> newCharges) {
        this.newCharges = newCharges;
    }

    
}
