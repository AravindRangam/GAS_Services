package com.kartha.cssp.data;

import org.springframework.stereotype.Component;

@Component
public class HourlyTempData {

    private String time;
    private String temp;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
