package com.kartha.cssp.data;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ProgramInfo implements Serializable {
    private String programName;
    private boolean text;
    private boolean voice;
    private String frequency;

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public boolean isText() {
        return text;
    }

    public void setText(boolean text) {
        this.text = text;
    }

    public boolean isVoice() {
        return voice;
    }

    public void setVoice(boolean voice) {
        this.voice = voice;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

}
