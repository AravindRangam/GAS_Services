package com.kartha.cssp.data;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DailyAvgTempData {

    private String date;
    private String dailyAvgTemp;
    List<HourlyTempData> houlryTempData;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDailyAvgTemp() {
        return dailyAvgTemp;
    }

    public void setDailyAvgTemp(String dailyAvgTemp) {
        this.dailyAvgTemp = dailyAvgTemp;
    }


}
