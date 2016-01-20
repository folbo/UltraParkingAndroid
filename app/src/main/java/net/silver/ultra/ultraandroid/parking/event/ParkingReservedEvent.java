package net.silver.ultra.ultraandroid.parking.event;

import net.silver.ultra.ultraandroid.parking.model.ReserveReturns;

/**
 * Created by Sylwekqaz on 16.01.2016.
 */

public class ParkingReservedEvent {
    private ReserveReturns reservedPlace;

    public ParkingReservedEvent(ReserveReturns cos) {
        this.reservedPlace = cos;
    }

    //getters & setters
    public ReserveReturns getReservedPlace() {
        return reservedPlace;
    }
}
