package com.kartha.cssp.data;

import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class TemperatureData {

    private String month;
    private String avg;
    List<DailyAvgTempData> dailyTempAvg;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

}
