package com.example.unifinder.Modal;

public class SeeAllBooking {

    int id;
    String bookingNo,driverId;
    String passengerName;
    String status;

    String fare, category, pickUp, dropOf;

    public SeeAllBooking(){
        this.id = id;
        this.bookingNo = bookingNo;
        this.driverId = driverId;
        this.passengerName = passengerName;
        this.status = status;
    }

    public String getFare () {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPickUp(String pickUp) {
        this.pickUp = pickUp;
    }

    public String getPickUp() {
        return pickUp;
    }

    public void setDropOf(String dropOf) {
        this.dropOf = dropOf;
    }

    public String getDropOf() {
        return dropOf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    public String getdriverId() {
        return driverId;
    }
    public void setdriverId(String driverId) {
        this.driverId = driverId;
    }

}
