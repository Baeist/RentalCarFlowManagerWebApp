package com.example.rentalcarflowmanagerwebapp.repository;

import com.example.rentalcarflowmanagerwebapp.model.UserModel;
import org.springframework.stereotype.Repository;
import com.example.rentalcarflowmanagerwebapp.utility.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class UserRepository {

    Connection connection;

    public UserModel getUserFromLogInNameAndPassword(String logInName, String employeePassword){

        try{
            connection = ConnectionManager.getConnection();

            final String SQL_QUERY = "SELECT * FROM employee WHERE employee_username = ? AND employee_password = ?";
            PreparedStatement ps = connection.prepareStatement(SQL_QUERY);
            ps.setString(1, logInName);
            ps.setString(2, employeePassword);
            ResultSet rs = ps.executeQuery();

                if(rs.next()) {
                    int employeeID = rs.getInt(1);
                    String firstName = rs.getString(2);
                    String lastName = rs.getString(3);
                    logInName = rs.getString(4);
                    employeePassword = rs.getString(5);
                    String employeeType = rs.getString(6);

                    UserModel user = new UserModel(employeeID, firstName, lastName, logInName, employeePassword, employeeType);

                    return user;
                }
        }
        catch (Exception e){
           e.printStackTrace();
        }
        return null;
    }


}
