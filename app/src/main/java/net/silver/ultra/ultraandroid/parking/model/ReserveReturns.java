package net.silver.ultra.ultraandroid.parking.model;

import java.io.Serializable;

public class ReserveReturns implements Serializable {
    private String segmentName;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }

    private Integer number;


}
