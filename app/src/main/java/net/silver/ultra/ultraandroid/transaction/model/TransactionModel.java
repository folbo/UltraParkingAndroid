package net.silver.ultra.ultraandroid.transaction.model;

public class TransactionModel {
    String startTime;
    String endTime;
    String parkingName;
    float price;

    @Override
    public String toString() {
        return "";
    }

    public TransactionModel() {
    }

    public TransactionModel(String startTime, String endTime, String parkingName, float price) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.parkingName = parkingName;
        this.price = price;
    }

    public String getParkingName() {
        return parkingName;
    }
    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
