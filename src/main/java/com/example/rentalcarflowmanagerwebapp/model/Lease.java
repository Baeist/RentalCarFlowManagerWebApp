package com.example.rentalcarflowmanagerwebapp.model;

import java.util.Date;

public class Lease {

    private Integer leaseID;
    private Date startDate;
    private int contractID;
    private int leasePeriodDays;
    private Car car;

    public Lease(int leaseID, Date startDate, int contractID, int leasePeriodDays, Car car) {
        this.leaseID = leaseID;
        this.startDate = startDate;
        this.contractID = contractID;
        this.leasePeriodDays = leasePeriodDays;
        this.car = car;
    }

    public Integer getLeaseID() {
        return leaseID;
    }
    public void setLeaseID(Integer leaseID) {
        this.leaseID = leaseID;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getContractID() {
        return contractID;
    }
    public void setContractID(int contractID) {
        this.contractID = contractID;
    }

    public int getLeasePeriodDays() {
        return leasePeriodDays;
    }
    public void setLeasePeriodDays(int leasePeriodDays) {
        this.leasePeriodDays = leasePeriodDays;
    }

    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }

    public String toString(){
        return "lease ID: " + leaseID + " | Car " + car;
    }
}
