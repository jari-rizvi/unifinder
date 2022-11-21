package com.example.unifinder.Modal;

public class UserCurrentBookingDetails {

    private int id;
    private String pickupTo;
    private String dropFrom;
    private String carName;
    private String carNo;
    private String day;
    private String time;
//    int id, String pickupTo, String dropFrom, String carName, String carNo, String day, String time
    public UserCurrentBookingDetails() {
        this.id = id;
        this.pickupTo = pickupTo;
        this.dropFrom = dropFrom;
        this.carName = carName;
        this.carNo = carNo;
        this.day = day;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPickupTo() {
        return pickupTo;
    }

    public void setPickupTo(String pickupTo) {
        this.pickupTo = pickupTo;
    }

    public String getDropFrom() {
        return dropFrom;
    }

    public void setDropFrom(String dropFrom) {
        this.dropFrom = dropFrom;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
