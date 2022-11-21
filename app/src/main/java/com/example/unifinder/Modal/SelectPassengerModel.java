package com.example.unifinder.Modal;

import android.os.Parcel;

public class SelectPassengerModel {

    int id;

    String name,gender,serviceStartDate,DateOfBirth,address;


    public SelectPassengerModel() {
       this.id = id;
       this.name = name;
       this.gender = gender;
       this.serviceStartDate = serviceStartDate;
       this.DateOfBirth = DateOfBirth;
       this.address = address;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getServiceStartDate() {
        return serviceStartDate;
    }

    public void setServiceStartDate(String serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
