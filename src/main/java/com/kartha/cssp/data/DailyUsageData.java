package com.kartha.cssp.data;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DailyUsageData {

    private String dailyDate;
    private String dailyBilledConsKwh;
    private String dailyBilledConsAmt;


    public String getDailyDate() {
        return dailyDate;
    }

    public void setDailyDate(String dailyDate) {
        this.dailyDate = dailyDate;
    }

    public String getDailyBilledConsKwh() {
        return dailyBilledConsKwh;
    }

    public void setDailyBilledConsKwh(String dailyBilledConsKwh) {
        this.dailyBilledConsKwh = dailyBilledConsKwh;
    }

    public String getDailyBilledConsAmt() {
        return dailyBilledConsAmt;
    }

    public void setDailyBilledConsAmt(String dailyBilledConsAmt) {
        this.dailyBilledConsAmt = dailyBilledConsAmt;
    }
    public List<HourlyUsageData> getHourlyUsage() {
        return hourlyUsage;
    }

    public void setHourlyUsage(List<HourlyUsageData> hourlyUsage) {
        this.hourlyUsage = hourlyUsage;
    }

    List<HourlyUsageData> hourlyUsage;

}
