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

    public TransactionModel(String startTime, String endTime, String parkingName, float price) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.parkingName = parkingName;
        this.price = price;
    }

    public String getParkingName() {
        return parkingName;
    }

    public float getPrice() {
        return price;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStartTime() {
        return startTime;
    }
}
