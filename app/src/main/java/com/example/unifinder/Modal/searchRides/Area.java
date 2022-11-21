package com.example.unifinder.Modal.searchRides;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Area  implements Serializable {

@SerializedName("VehicleId")
@Expose
private String vehicleId;
@SerializedName("VehicleModel")
@Expose
private String vehicleModel;
@SerializedName("RegistrationNumber")
@Expose
private String registrationNumber;
@SerializedName("VehicleColor")
@Expose
private String vehicleColor;
@SerializedName("DriverName")
@Expose
private String driverName;
@SerializedName("dropAreaCity")
@Expose
private String dropAreaCity;
@SerializedName("dropAreaArea")
@Expose
private String dropAreaArea;
@SerializedName("dropAreaSector")
@Expose
private String dropAreaSector;
@SerializedName("fare")
@Expose
private String fare;

public String getVehicleId() {
return vehicleId;
}

public void setVehicleId(String vehicleId) {
this.vehicleId = vehicleId;
}

public String getVehicleModel() {
return vehicleModel;
}

public void setVehicleModel(String vehicleModel) {
this.vehicleModel = vehicleModel;
}

public String getRegistrationNumber() {
return registrationNumber;
}

public void setRegistrationNumber(String registrationNumber) {
this.registrationNumber = registrationNumber;
}

public String getVehicleColor() {
return vehicleColor;
}

public void setVehicleColor(String vehicleColor) {
this.vehicleColor = vehicleColor;
}

public String getDriverName() {
return driverName;
}

public void setDriverName(String driverName) {
this.driverName = driverName;
}

public String getDropAreaCity() {
return dropAreaCity;
}

public void setDropAreaCity(String dropAreaCity) {
this.dropAreaCity = dropAreaCity;
}

public String getDropAreaArea() {
return dropAreaArea;
}

public void setDropAreaArea(String dropAreaArea) {
this.dropAreaArea = dropAreaArea;
}

public String getDropAreaSector() {
return dropAreaSector;
}

public void setDropAreaSector(String dropAreaSector) {
this.dropAreaSector = dropAreaSector;
}


public String getFare() {
    return fare;
}

public void setFare(String fare) {
    this.fare = fare;
}



}