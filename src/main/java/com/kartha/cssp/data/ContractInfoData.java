package com.kartha.cssp.data;

public class ContractInfoData {

    private String typeOfBusiness;
    private String contractId;
    private String accountStatus;
    private String rateType;
    private String moveInDate;
    private String moveOutDate;
    private String paidDeposit;
    private String rate;
    private String flatRate;
    private String tax;
    private String multiplier;
    private String currentBillAmount;
    private String currentBillDate;
    private OtherChargesData otherCharges;
    private String totalAmountDue;
    private String dueDate;
    private String nextBillDate;
    private String nextBillEndDate;
    private String nextMonthCycleDays;
    private String projBillAmount;
    private String projBaseCharge;
    private String projBillKWH;
    private String asOfDayConsumption;
    private String asOfDayKWHUsage;
    private Boolean multiDueFlag;
    private Boolean pastDueFlag;
    private String lastPaymentAmt;
    private String lastPendingPaymentAmount;
    private String lastPaymentDate;
    private String lastPendingPaymentDate;
    private Boolean balanceNotInvoiced;
    private String uninvoicedDeposit;
    private String adjustedPastDue;
    private String paymentArrangementAmount;
    private String paymentArrangementDate;
    private PickupServicesData pickupServices;

    public String getTypeOfBusiness() {
        return typeOfBusiness;
    }

    public void setTypeOfBusiness(String typeOfBusiness) {
        this.typeOfBusiness = typeOfBusiness;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getMoveInDate() {        return moveInDate;    }

    public void setMoveInDate(String moveInDate) {        this.moveInDate = moveInDate;    }

    public String getMoveOutDate() {        return moveOutDate;    }

    public void setMoveOutDate(String moveOutDate) {        this.moveOutDate = moveOutDate;    }

    public String getPaidDeposit() {
        return paidDeposit;
    }

    public void setPaidDeposit(String paidDeposit) {
        this.paidDeposit = paidDeposit;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getFlatRate() {
        return flatRate;
    }

    public void setFlatRate(String flatRate) {
        this.flatRate = flatRate;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(String multiplier) {
        this.multiplier = multiplier;
    }

    public String getCurrentBillAmount() {
        return currentBillAmount;
    }

    public void setCurrentBillAmount(String currentBillAmount) {
        this.currentBillAmount = currentBillAmount;
    }

    public String getCurrentBillDate() {
        return currentBillDate;
    }

    public void setCurrentBillDate(String currentBillDate) {
        this.currentBillDate = currentBillDate;
    }

    public String getTotalAmountDue() {
        return totalAmountDue;
    }

    public void setTotalAmountDue(String totalAmountDue) {
        this.totalAmountDue = totalAmountDue;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getNextBillDate() {
        return nextBillDate;
    }

    public void setNextBillDate(String nextBillDate) {
        this.nextBillDate = nextBillDate;
    }

    public String getNextBillEndDate() {
        return nextBillEndDate;
    }

    public void setNextBillEndDate(String nextBillEndDate) {
        this.nextBillEndDate = nextBillEndDate;
    }

    public String getNextMonthCycleDays() {
        return nextMonthCycleDays;
    }

    public void setNextMonthCycleDays(String nextMonthCycleDays) {
        this.nextMonthCycleDays = nextMonthCycleDays;
    }

    public String getProjBillAmount() {
        return projBillAmount;
    }

    public void setProjBillAmount(String projBillAmount) {
        this.projBillAmount = projBillAmount;
    }

    public String getProjBaseCharge() {
        return projBaseCharge;
    }

    public void setProjBaseCharge(String projBaseCharge) {
        this.projBaseCharge = projBaseCharge;
    }

    public String getProjBillKWH() {
        return projBillKWH;
    }

    public void setProjBillKWH(String projBillKWH) {
        this.projBillKWH = projBillKWH;
    }

    public String getAsOfDayConsumption() {
        return asOfDayConsumption;
    }

    public void setAsOfDayConsumption(String asOfDayConsumption) {
        this.asOfDayConsumption = asOfDayConsumption;
    }

    public String getAsOfDayKWHUsage() {
        return asOfDayKWHUsage;
    }

    public void setAsOfDayKWHUsage(String asOfDayKWHUsage) {
        this.asOfDayKWHUsage = asOfDayKWHUsage;
    }

    public Boolean getMultiDueFlag() {
        return multiDueFlag;
    }

    public void setMultiDueFlag(Boolean multiDueFlag) {
        this.multiDueFlag = multiDueFlag;
    }

    public Boolean getPastDueFlag() {
        return pastDueFlag;
    }

    public void setPastDueFlag(Boolean pastDueFlag) {
        this.pastDueFlag = pastDueFlag;
    }

    public String getLastPaymentAmt() {
        return lastPaymentAmt;
    }

    public void setLastPaymentAmt(String lastPaymentAmt) {
        this.lastPaymentAmt = lastPaymentAmt;
    }

    public String getLastPendingPaymentAmount() {
        return lastPendingPaymentAmount;
    }

    public void setLastPendingPaymentAmount(String lastPendingPaymentAmount) {
        this.lastPendingPaymentAmount = lastPendingPaymentAmount;
    }

    public String getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(String lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public String getLastPendingPaymentDate() {
        return lastPendingPaymentDate;
    }

    public void setLastPendingPaymentDate(String lastPendingPaymentDate) {
        this.lastPendingPaymentDate = lastPendingPaymentDate;
    }

    public Boolean getBalanceNotInvoiced() {
        return balanceNotInvoiced;
    }

    public void setBalanceNotInvoiced(Boolean balanceNotInvoiced) {
        this.balanceNotInvoiced = balanceNotInvoiced;
    }

    public String getUninvoicedDeposit() {
        return uninvoicedDeposit;
    }

    public void setUninvoicedDeposit(String uninvoicedDeposit) {
        this.uninvoicedDeposit = uninvoicedDeposit;
    }

    public String getAdjustedPastDue() {
        return adjustedPastDue;
    }

    public void setAdjustedPastDue(String adjustedPastDue) {
        this.adjustedPastDue = adjustedPastDue;
    }

    public String getPaymentArrangementAmount() { return paymentArrangementAmount; }

    public void setPaymentArrangementAmount(String paymentArrangementAmount) { this.paymentArrangementAmount = paymentArrangementAmount; }

    public String getPaymentArrangementDate() { return paymentArrangementDate; }

    public void setPaymentArrangementDate(String paymentArrangementDate) {this.paymentArrangementDate = paymentArrangementDate; }

    public OtherChargesData getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(OtherChargesData otherCharges) {
        this.otherCharges = otherCharges;
    }

    public PickupServicesData getPickupServices() {
        return pickupServices;
    }

    public void setPickupServices(PickupServicesData pickupServices) {
        this.pickupServices = pickupServices;
    }
}
