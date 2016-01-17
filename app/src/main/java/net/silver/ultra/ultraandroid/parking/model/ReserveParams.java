package net.silver.ultra.ultraandroid.parking.model;

import java.io.Serializable;

import javax.crypto.SecretKey;

/**
 * Created by Sylwekqaz on 17.01.2016.
 */
public class ReserveParams implements Serializable {
    private String parkingId;

    public ReserveParams(String parkingId) {
        this.parkingId = parkingId;
    }

    public String getParkingId() {
        return parkingId;
    }

    public void setParkingId(String parkingId) {
        this.parkingId = parkingId;
    }
}

