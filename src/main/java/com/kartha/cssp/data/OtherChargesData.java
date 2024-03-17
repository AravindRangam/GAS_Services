package com.kartha.cssp.data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class OtherChargesData implements Serializable {
    private String wasteWaterBill;
    private String drainageFee;
    private String trashServices;
    private String miscFee;

    public String getWasteWaterBill() {
        return wasteWaterBill;
    }

    public void setWasteWaterBill(String wasteWaterBill) {
        this.wasteWaterBill = wasteWaterBill;
    }

    public String getDrainageFee() {
        return drainageFee;
    }

    public void setDrainageFee(String drainageFee) {
        this.drainageFee = drainageFee;
    }

    public String getTrashServices() {
        return trashServices;
    }

    public void setTrashServices(String trashServices) {
        this.trashServices = trashServices;
    }

    public String getMiscFee() {
        return miscFee;
    }

    public void setMiscFee(String miscFee) {
        this.miscFee = miscFee;
    }

}
