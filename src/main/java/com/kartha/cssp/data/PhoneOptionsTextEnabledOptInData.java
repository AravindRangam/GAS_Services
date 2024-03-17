package com.kartha.cssp.data;

import org.springframework.stereotype.Component;
import java.util.List;

import java.io.Serializable;

@Component
public class PhoneOptionsTextEnabledOptInData implements Serializable {
    private String priority;
    private boolean textEnabled;
    private boolean optIn;
    private String textStartTime;
    private String textEndTime;
    private String voiceStartTime;
    private String voiceEndTime;
    private List<ProgramInfo> programInfo;

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isTextEnabled() {
        return textEnabled;
    }

    public void setTextEnabled(boolean textEnabled) {
        this.textEnabled = textEnabled;
    }

    public boolean isOptIn() {
        return optIn;
    }

    public void setOptIn(boolean optIn) {
        this.optIn = optIn;
    }

    public String getTextStartTime() {
        return textStartTime;
    }

    public void setTextStartTime(String textStartTime) {
        this.textStartTime = textStartTime;
    }

    public String getTextEndTime() {
        return textEndTime;
    }

    public void setTextEndTime(String textEndTime) {
        this.textEndTime = textEndTime;
    }

    public String getVoiceStartTime() {
        return voiceStartTime;
    }

    public void setVoiceStartTime(String voiceStartTime) {
        this.voiceStartTime = voiceStartTime;
    }

    public String getVoiceEndTime() {
        return voiceEndTime;
    }

    public void setVoiceEndTime(String voiceEndTime) {
        this.voiceEndTime = voiceEndTime;
    }

    public List<ProgramInfo> getProgramInfo() {
        return programInfo;
    }

    public void setProgramInfo(List<ProgramInfo> programInfo) {
        this.programInfo = programInfo;
    }

}
