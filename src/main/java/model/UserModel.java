package model;

import org.apache.catalina.User;

public class UserModel {

    private int employeeID;
    private String firstName;
    private String lastName;
    private String logInName;
    private String employeePassword;
    private String employeeType;

    public UserModel(){}

    public UserModel(int employeeID, String firstName, String lastName, String logInName, String employeePassword, String employeeType){

        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.logInName = logInName;
        this.employeePassword = employeePassword;
        this.employeeType = employeeType;

    }
}
