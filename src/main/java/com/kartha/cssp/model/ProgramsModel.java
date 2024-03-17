package com.kartha.cssp.model;

import com.kartha.cssp.data.ProgramsData;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@Document(collection = "programs")
public class ProgramsModel {

    @Id
    private String id;

    private String accountNumber;

    private List<ProgramsData> programInfo;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<ProgramsData> getProgramInfo() {
        return programInfo;
    }

    public void setProgramInfo(List<ProgramsData> programInfo) {
        this.programInfo = programInfo;
    }
}