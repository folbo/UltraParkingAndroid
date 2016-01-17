package net.silver.ultra.ultraandroid.parking.event;

import net.silver.ultra.ultraandroid.parking.model.ReserveReturns;

/**
 * Created by Sylwekqaz on 16.01.2016.
 */
public class ParkingReservedEvent {

    public ReserveReturns getReservedPlace() {
        return reservedPlace;
    }

    private ReserveReturns reservedPlace;

    public ParkingReservedEvent(ReserveReturns cos) {
        this.reservedPlace = cos;
    }

}
