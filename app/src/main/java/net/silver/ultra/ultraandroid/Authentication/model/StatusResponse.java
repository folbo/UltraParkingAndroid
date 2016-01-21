package net.silver.ultra.ultraandroid.Authentication.model;

import java.util.UUID;

/**
 * Created by folbo on 2016-01-21.
 */
public class StatusResponse {
    UUID id;
    String firstName;
    String lastName;
    float currency;
    String carId;
    UUID reserverParkingId;

    // getters & setters
    public void setCarId(String carId) {
        this.carId = carId;
    }
    public String getCarId() {
        return carId;
    }

    public void setCurrency(float currency) {
        this.currency = currency;
    }
    public float getCurrency() {
        return currency;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getId() {
        return id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setReserverParkingId(UUID reserverParkingId) {
        this.reserverParkingId = reserverParkingId;
    }
    public UUID getReserverParkingId() {
        return reserverParkingId;
    }
}
