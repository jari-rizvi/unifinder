package com.example.unifinder.RegisterPassenger;

public class WorkShopModel {

    String workShopTitle;
    String workShopDetails;
    String workShopTime;
    Boolean workshopActive;

    public WorkShopModel() {
    }

    public WorkShopModel(String workShopTitle, String workShopDetails, String workShopTime, Boolean workshopActive) {
        this.workShopTitle = workShopTitle;
        this.workShopDetails = workShopDetails;
        this.workShopTime = workShopTime;
        this.workshopActive = workshopActive;
    }

    public String getWorkShopTitle() {
        return workShopTitle;
    }

    public void setWorkShopTitle(String workShopTitle) {
        this.workShopTitle = workShopTitle;
    }

    public String getWorkShopDetails() {
        return workShopDetails;
    }

    public void setWorkShopDetails(String workShopDetails) {
        this.workShopDetails = workShopDetails;
    }

    public String getWorkShopTime() {
        return workShopTime;
    }

    public void setWorkShopTime(String workShopTime) {
        this.workShopTime = workShopTime;
    }

    public Boolean getWorkshopActive() {
        return workshopActive;
    }

    public void setWorkshopActive(Boolean workshopActive) {
        this.workshopActive = workshopActive;
    }
}
