package com.example.rentalcarflowmanagerwebapp.model;

public class Car {

    private int carID;
    private String chassisNumber;
    private String color;
    private String manufacturer;
    private String carType;
    private String carName;
    private int gearLevel;      // no clue what this is, we are using an int, but really it could be anything
    private double steelPriceDKK;      // price before taxes and any other fees
    private double registrationFeeDKK;
    private double CO2EmissionPerKM;     // no idea about which unit here ppm/something??
    private double carPricePerMonthDKK;

    public Car(){}

    public Car(int carID, String chassisNumber, String color, String manufacturer, String carType,
               String carName, int gearLevel, double steelPriceDKK, double registrationFeeDKK,
               double CO2EmissionPerKM, double carPricePerMonthDKK) {
        this.carID = carID;
        this.chassisNumber = chassisNumber;
        this.color = color;
        this.manufacturer = manufacturer;
        this.carType = carType;
        this.carName = carName;
        this.gearLevel = gearLevel;
        this.steelPriceDKK = steelPriceDKK;
        this.registrationFeeDKK = registrationFeeDKK;
        this.CO2EmissionPerKM = CO2EmissionPerKM;
        this.carPricePerMonthDKK = carPricePerMonthDKK;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getGearLevel() {
        return gearLevel;
    }

    public void setGearLevel(int gearLevel) {
        this.gearLevel = gearLevel;
    }

    public double getSteelPriceDKK() {
        return steelPriceDKK;
    }

    public void setSteelPriceDKK(double steelPriceDKK) {
        this.steelPriceDKK = steelPriceDKK;
    }

    public double getRegistrationFeeDKK() {
        return registrationFeeDKK;
    }

    public void setRegistrationFeeDKK(double registrationFeeDKK) {
        this.registrationFeeDKK = registrationFeeDKK;
    }

    public double getCO2EmissionPerKM() {
        return CO2EmissionPerKM;
    }

    public void setCO2EmissionPerKM(double CO2EmissionPerKM) {
        this.CO2EmissionPerKM = CO2EmissionPerKM;
    }

    public double getCarPricePerMonthDKK() {
        return carPricePerMonthDKK;
    }

    public void setCarPricePerMonthDKK(double carPricePerMonthDKK) {
        this.carPricePerMonthDKK = carPricePerMonthDKK;
    }
}
