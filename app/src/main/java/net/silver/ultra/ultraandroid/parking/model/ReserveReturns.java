package net.silver.ultra.ultraandroid.parking.model;

import java.io.Serializable;

public class ReserveReturns implements Serializable {
    private String segmentName;
    private Integer number;

    //getters & setters
    public Integer getNumber() {
        return number;
    }
    public Integer setNumber(Integer number) {
        return this.number = number;
    }

    public String getSegmentName() {
        return segmentName;
    }
    public String setSegmentName(String segmentName) {
        return this.segmentName = segmentName;
    }
}
