package com.example.rentalcarflowmanagerwebapp.model;

public class Contract {
    private int contractID;
    private int customerID;
    private int employeeID;

    public Contract(int contractID, int customerID, int employeeID) {
        this.contractID = contractID;
        this.customerID = customerID;
        this.employeeID = employeeID;
    }

    public Integer getContractID() {
        return contractID;
    }
    public void setContractID(Integer contractID) {
        this.contractID = contractID;
    }

    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getEmployeeID() {
        return employeeID;
    }
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

}
