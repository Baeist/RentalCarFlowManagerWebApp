package com.example.rentalcarflowmanagerwebapp.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Status {


    private int statusID;
    private int carID;
    private String statusDescription;
    private LocalDate startDate;
    private LocalDate endDate;

    public Status(){}

    public Status(int statusID, int carID, String statusDescription, LocalDate startDate, LocalDate endDate){
        this.statusID = statusID;
        this.carID = carID;
        this.statusDescription = statusDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
