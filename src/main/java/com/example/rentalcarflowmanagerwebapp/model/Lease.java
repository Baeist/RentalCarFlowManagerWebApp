package com.example.rentalcarflowmanagerwebapp.model;

import java.time.LocalDate;
import java.util.Date;

// Tobias
public class Lease {

    private int leaseID;
    private LocalDate startDate;
    private int contractID;
    private int leasePeriodDays;
    private int carID;

    // used for displaying create new damage report
    private boolean hasDamageReport;

    public Lease(int leaseID, LocalDate startDate, int contractID, int leasePeriodDays, int carID) {
        this.leaseID = leaseID;
        this.startDate = startDate;
        this.contractID = contractID;
        this.leasePeriodDays = leasePeriodDays;
        this.carID = carID;
    }

    public int getLeaseID() {
        return leaseID;
    }
    public void setLeaseID(int leaseID) {
        this.leaseID = leaseID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
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

    public int getCarID() {
        return carID;
    }
    public void setCarID(int carID) {
        this.carID = carID;
    }

    public boolean isHasDamageReport() {
        return hasDamageReport;
    }
    public void setHasDamageReport(boolean hasDamageReport) {
        this.hasDamageReport = hasDamageReport;
    }

    public String toString(){
        return "lease ID: " + leaseID;
    }


}
