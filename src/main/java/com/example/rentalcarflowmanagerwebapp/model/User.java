package com.example.rentalcarflowmanagerwebapp.model;

public class User {

    private int employeeID;
    private String firstName;
    private String lastName;
    private String logInName;
    private String employeePassword;
    private String employeeSalt;
    private String employeeType;
    private boolean isUserActive;

    public User(){}

    public User(int employeeID, String firstName, String lastName, String logInName, String employeePassword, String employeeSalt, String employeeType, boolean isUserActive){

        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.logInName = logInName;
        this.employeePassword = employeePassword;
        this.employeeSalt = employeeSalt;
        this.employeeType = employeeType;
        this.isUserActive = isUserActive;

    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogInName(String logInName) {
        this.logInName = logInName;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public void setUserActive(boolean userActive) {
        isUserActive = userActive;
    }

    public void setEmployeeSalt(String employeeSalt) {
        this.employeeSalt = employeeSalt;
    }

    public boolean isUserActive() {
        return isUserActive;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeSalt() {
        return employeeSalt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogInName() {
        return logInName;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public String getEmployeeType() {
        return employeeType;
    }
}
