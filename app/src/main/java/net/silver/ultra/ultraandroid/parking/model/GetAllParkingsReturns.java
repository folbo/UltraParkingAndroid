package net.silver.ultra.ultraandroid.parking.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by folbo on 2016-01-17.
 */
public class GetAllParkingsReturns implements Serializable{
    /*List<ParkingModel> parkings = new ArrayList<>();;

    public List<ParkingModel> getParkings() { return parkings; }
    public List<ParkingModel> setParkings(List<ParkingModel> parkings) { return this.parkings = parkings; }*/

    UUID id;
    String name;
    int totalPlacesCount;
    int freePlacesCount;
    UUID ownerId;
    double locationLatitude;
    double locationLongitude;

    public UUID setId(UUID id){ return this.id = id; }
    public UUID getId(){ return this.id; }

    public String setName(String name){ return this.name = name; }
    public String getName(){ return this.name; }

    public int setTotalPlacesCount(int totalPlacesCount){ return this.totalPlacesCount = totalPlacesCount; }
    public int getTotalPlacesCount(){ return this.totalPlacesCount; }

    public int setFreePlacesCount(int freePlacesCount){ return this.freePlacesCount = freePlacesCount; }
    public int getFreePlacesCount(){ return this.freePlacesCount; }

    public UUID setOwnerId(UUID ownerId){ return this.ownerId = ownerId; }
    public UUID getOwnerId(){ return this.ownerId; }

    public double setLocationLatitude(double locationLatitude){ return this.locationLatitude = locationLatitude; }
    public double getLocationLatitude(){ return this.locationLatitude; }

    public double setLocationLongitude(double locationLongitude){ return this.locationLongitude = locationLongitude; }
    public double getLocationLongitude(){ return this.locationLongitude; }
}
