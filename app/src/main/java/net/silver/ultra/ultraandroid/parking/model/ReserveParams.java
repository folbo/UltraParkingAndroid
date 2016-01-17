package net.silver.ultra.ultraandroid.parking.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Sylwekqaz on 17.01.2016.
 */
public class ReserveParams implements Serializable {
    private UUID parkingId;

    public ReserveParams(UUID parkingId) {
        this.parkingId = parkingId;
    }

    // getters & setters
    public UUID getParkingId() {
        return parkingId;
    }
    public UUID setParkingId(UUID parkingId) {
        return this.parkingId = parkingId;
    }
}

