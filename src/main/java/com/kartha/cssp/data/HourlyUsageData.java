package com.kartha.cssp.data;

import org.springframework.stereotype.Component;

@Component
public class HourlyUsageData {

    private String time;
    private String hrConsKwh;
    private String hrBilledConsAmt;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHrConsKwh() {
        return hrConsKwh;
    }

    public void setHrConsKwh(String hrConsKwh) {
        this.hrConsKwh = hrConsKwh;
    }

    public String getHrBilledConsAmt() {
        return hrBilledConsAmt;
    }

    public void setHrBilledConsAmt(String hrBilledConsAmt) {
        this.hrBilledConsAmt = hrBilledConsAmt;
    }

}
