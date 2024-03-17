package com.kartha.cssp.model;
import com.kartha.cssp.data.BankData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "bank_info")
public class BankDetailsInfo {

    @Id
    private String id;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<BankData> getBank_info() {
        return bank_info;
    }

    public void setBank_info(List<BankData> bank_info) {
        this.bank_info = bank_info;
    }

    private String accountNumber;
        private List<BankData> bank_info;
}
