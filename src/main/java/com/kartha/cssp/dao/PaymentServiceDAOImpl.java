package com.kartha.cssp.dao;

import com.kartha.cssp.data.BankData;
import com.kartha.cssp.data.ContractInfoData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.model.Account;
import com.kartha.cssp.model.BankDetailsInfo;
import com.kartha.cssp.request.MakePaymentRequest;
import com.kartha.cssp.utils.CSSPConstants;
import com.kartha.cssp.utils.EmailServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
public class PaymentServiceDAOImpl implements PaymentServiceDAO {

    MongoTemplate mongodbTemplate;

    EmailServiceUtil emailServiceUtil;

    AccountSummaryServiceDAO accountSummaryServiceDAO;

    @Autowired
    public PaymentServiceDAOImpl(MongoTemplate mongodbTemplate, EmailServiceUtil emailServiceUtil,
                                 AccountSummaryServiceDAO accountSummaryServiceDAO) {
        this.mongodbTemplate = mongodbTemplate;
        this.emailServiceUtil = emailServiceUtil;
        this.accountSummaryServiceDAO = accountSummaryServiceDAO;
    }

    public BankData retrieveBankDetails(String accountNumber, String requestType, BankData bankData) throws CSSPServiceException {

        Query query = new Query();
        query.addCriteria(Criteria.where("accountNumber").is((accountNumber)));
        BankDetailsInfo bankDetailsInfo = mongodbTemplate.findOne(query, BankDetailsInfo.class);
        if(Objects.nonNull(bankDetailsInfo)){
            List<BankData> bankDataList = bankDetailsInfo.getBank_info();
            if(Objects.nonNull(bankDataList)) {

                for(BankData bankData1: bankDataList) {
                    if(Objects.nonNull(bankData1)) {
                        if (bankData1.getPaymentType().equalsIgnoreCase(requestType)) {
                            bankData1.setBankAccountNo(bankData1.getBankAccountNo().replaceAll("\\d(?=(?:\\D*\\d){4})", "*"));
                            bankData = bankData1;
                        }
                    }
                }

            } else {
                throw new CSSPServiceException("account.not.found 1","Payment Details not exist.");
            }
        } else {
            throw new CSSPServiceException("account.not.found","Payment Details not exist.");
        }
        return bankData;
    }

    public String makePaymentPayNow(MakePaymentRequest makePaymentRequest, String accountNumber) throws CSSPServiceException {
        //Account_Info
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("accountNumber").is(accountNumber));
            Account account = mongodbTemplate.findOne(query, Account.class);
            ContractInfoData contractInfoData = account.getContractInfo().get(0);

            Float paymentAmount = Float.valueOf(makePaymentRequest.getAmount());
            Float totalAmountDue = Float.valueOf(contractInfoData.getTotalAmountDue());
            Float currentBillAmount = Float.valueOf(contractInfoData.getCurrentBillAmount());
            Float adjustedPastDue = Float.valueOf(contractInfoData.getAdjustedPastDue());

            if (paymentAmount >= adjustedPastDue)
                adjustedPastDue = 0.00f;
            else
                adjustedPastDue = adjustedPastDue - paymentAmount;

            if (currentBillAmount > (totalAmountDue - paymentAmount)) {
                currentBillAmount = currentBillAmount + (totalAmountDue - currentBillAmount) - paymentAmount;
            }

            totalAmountDue = totalAmountDue - paymentAmount;

            contractInfoData.setCurrentBillAmount(String.valueOf(String.format("%.2f", currentBillAmount)));
            contractInfoData.setTotalAmountDue(String.valueOf(String.format("%.2f", totalAmountDue)));
            contractInfoData.setAdjustedPastDue(String.valueOf(String.format("%.2f", adjustedPastDue)));

            account = this.mongodbTemplate.save(account);

            emailServiceUtil.sendEmailRequest(accountSummaryServiceDAO.getAccountSummaryLite(account.getAccountNumber()), CSSPConstants.EMAIL_MAKE_PAYMENT);

        } catch (Exception e) {
            throw new CSSPServiceException("payment.submit.error","Payment submit error");
        }
        return "SUCCESS";
    }
}
