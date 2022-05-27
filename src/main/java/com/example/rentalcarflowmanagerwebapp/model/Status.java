package com.example.rentalcarflowmanagerwebapp.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// Lars
public class Status {


    private int statusID;
    private int carID;
    private String statusDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private int daysLeft;

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public Status(){}

    public Status(int carID, String statusDescription, int daysLeft){
        this.carID = carID;
        this.statusDescription = statusDescription;
        this.daysLeft = daysLeft;
    }

    public Status(int statusID, int carID, String statusDescription, int daysLeft){
        this.statusID = statusID;
        this.carID = carID;
        this.statusDescription = statusDescription;
        this.daysLeft = daysLeft;
    }
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
