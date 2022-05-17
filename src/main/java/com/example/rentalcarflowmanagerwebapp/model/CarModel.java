package com.example.rentalcarflowmanagerwebapp.model;

public class CarModel {

    private int carID;
    private String chassisNumber;
    private String color;
    private String manufacturer;
    private String carType;
    private String carName;
    private double carPricePerMonthDKK;

    public CarModel(int carID, String chassisNumber, String color, String manufacturer, String carType, String carName, double carPricePerMonthDKK) {
        this.carID = carID;
        this.chassisNumber = chassisNumber;
        this.color = color;
        this.manufacturer = manufacturer;
        this.carType = carType;
        this.carName = carName;
        this.carPricePerMonthDKK = carPricePerMonthDKK;
    }

    public int getCarID() {
        return carID;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public String getColor() {
        return color;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getCarType() {
        return carType;
    }

    public String getCarName() {
        return carName;
    }

    public double getCarPricePerMonthDKK() {
        return carPricePerMonthDKK;
    }
}
