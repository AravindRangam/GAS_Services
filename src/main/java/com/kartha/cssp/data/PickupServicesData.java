package com.kartha.cssp.data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class PickupServicesData implements Serializable {

    private String trashPickup;
    private String trashAndRecycle;
    private String bulkPickUp;

    public String getTrashPickup() {
        return trashPickup;
    }

    public void setTrashPickup(String trashPickup) {
        this.trashPickup = trashPickup;
    }

    public String getTrashAndRecycle() {
        return trashAndRecycle;
    }

    public void setTrashAndRecycle(String trashAndRecycle) {
        this.trashAndRecycle = trashAndRecycle;
    }

    public String getBulkPickUp() {
        return bulkPickUp;
    }

    public void setBulkPickUp(String bulkPickUp) {
        this.bulkPickUp = bulkPickUp;
    }

}
