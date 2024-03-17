package com.kartha.cssp.data;

import org.springframework.stereotype.Component;

@Component
public class ProgramsData {

    private String programType;
    private String enrolled;
    private String createdTs;
    private String updatedTs;
    private boolean eligibleFlag;

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public String getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(String enrolled) {
        this.enrolled = enrolled;
    }

    public String getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(String createdTs) {
        this.createdTs = createdTs;
    }

    public String getUpdatedTs() {
        return updatedTs;
    }

    public void setUpdatedTs(String updatedTs) {
        this.updatedTs = updatedTs;
    }

    public boolean isEligibleFlag() {
        return eligibleFlag;
    }

    public void setEligibleFlag(boolean eligibleFlag) {
        this.eligibleFlag = eligibleFlag;
    }
}
