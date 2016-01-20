package net.silver.ultra.ultraandroid.transaction.model;

public class TransactionModel {
    String dateStart;
    String dateEnd;
    String parkingName;
    float money;

    @Override
    public String toString() {
        return "";
    }

    public TransactionModel(String dateStart, String dateEnd, String parkingName, float money) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.parkingName = parkingName;
        this.money = money;
    }

    public String getParkingName() {
        return parkingName;
    }

    public float getMoney() {
        return money;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public String getDateStart() {
        return dateStart;
    }
}
