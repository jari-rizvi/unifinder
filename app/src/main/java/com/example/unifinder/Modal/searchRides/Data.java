package com.example.unifinder.Modal.searchRides;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {

    @SerializedName("pickAreaCity")
    @Expose
    private String pickAreaCity;
    @SerializedName("pickAreaArea")
    @Expose
    private String pickAreaArea;
    @SerializedName("pickAreaSector")
    @Expose
    private String pickAreaSector;
    @SerializedName("areas")
    @Expose
    private List<Area> areas = null;

    public String getPickAreaCity() {
        return pickAreaCity;
    }

    public void setPickAreaCity(String pickAreaCity) {
        this.pickAreaCity = pickAreaCity;
    }

    public String getPickAreaArea() {
        return pickAreaArea;
    }

    public void setPickAreaArea(String pickAreaArea) {
        this.pickAreaArea = pickAreaArea;
    }

    public String getPickAreaSector() {
        return pickAreaSector;
    }

    public void setPickAreaSector(String pickAreaSector) {
        this.pickAreaSector = pickAreaSector;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

}