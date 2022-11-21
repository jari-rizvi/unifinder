package com.example.unifinder.Modal;

public class RouteDetailList {

    int id;
    String routePickup;
    String routeDropOff;
    String routePickupTime;
    String routeDropTime;
    String vehicleCarModelName;
    String vehicleCarNumber;
    String vehicleCarColor;
    String vehicleCarDriverName;
//    String vehicleCarRating;

    public RouteDetailList(int id,
                           String routePickup,
                           String routeDropOff,
                           String routePickupTime,
                           String routeDropTime,
                           String vehicleCarModelName,
                           String vehicleCarNumber,
                           String vehicleCarColor,
                           String vehicleCarDriverName) {
        this.id = id;
        this.routePickup = routePickup;
        this.routeDropOff = routeDropOff;
        this.routePickupTime = routePickupTime;
        this.routeDropTime = routeDropTime;
        this.vehicleCarModelName = vehicleCarModelName;
        this.vehicleCarNumber = vehicleCarNumber;
        this.vehicleCarColor = vehicleCarColor;
        this.vehicleCarDriverName = vehicleCarDriverName;
//        this.vehicleCarRating = vehicleCarRating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoutePickup() {
        return routePickup;
    }

    public void setRoutePickup(String routePickup) {
        this.routePickup = routePickup;
    }

    public String getRouteDropOff() {
        return routeDropOff;
    }

    public void setRouteDropOff(String routeDropOff) {
        this.routeDropOff = routeDropOff;
    }

    public String getRoutePickupTime() {
        return routePickupTime;
    }

    public void setRoutePickupTime(String routePickupTime) {
        this.routePickupTime = routePickupTime;
    }

    public String getRouteDropTime() {
        return routeDropTime;
    }

    public void setRouteDropTime(String routeDropTime) {
        this.routeDropTime = routeDropTime;
    }

    public String getVehicleCarModelName() {
        return vehicleCarModelName;
    }

    public void setVehicleCarModelName(String vehicleCarModelName) {
        this.vehicleCarModelName = vehicleCarModelName;
    }

    public String getVehicleCarNumber() {
        return vehicleCarNumber;
    }

    public void setVehicleCarNumber(String vehicleCarNumber) {
        this.vehicleCarNumber = vehicleCarNumber;
    }

    public String getVehicleCarColor() {
        return vehicleCarColor;
    }

    public void setVehicleCarColor(String vehicleCarColor) {
        this.vehicleCarColor = vehicleCarColor;
    }

    public String getVehicleCarDriverName() {
        return vehicleCarDriverName;
    }

    public void setVehicleCarDriverName(String vehicleCarDriverName) {
        this.vehicleCarDriverName = vehicleCarDriverName;
    }

//    public String getVehicleCarRating() {
//        return vehicleCarRating;
//    }
//
//    public void setVehicleCarRating(String vehicleCarRating) {
//        this.vehicleCarRating = vehicleCarRating;
//    }
}
