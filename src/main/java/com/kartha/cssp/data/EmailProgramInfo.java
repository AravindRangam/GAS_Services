package com.kartha.cssp.data;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class EmailProgramInfo implements Serializable {
    private String programName;
    private boolean email;
    private String frequency;

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public boolean isEmail() {
        return email;
    }

    public void setEmail(boolean email) {
        this.email = email;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

}
