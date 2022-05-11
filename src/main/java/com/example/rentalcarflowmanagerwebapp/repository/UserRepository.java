package com.example.rentalcarflowmanagerwebapp.repository;

import com.example.rentalcarflowmanagerwebapp.model.UserModel;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;
import com.example.rentalcarflowmanagerwebapp.utility.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
                    boolean isUserActive = rs.getBoolean(7);

                    UserModel user = new UserModel(employeeID, firstName, lastName, logInName, employeePassword, employeeType, isUserActive);

                    return user;
                }
        }
        catch (Exception e){
           e.printStackTrace();
        }
        return null;
    }

    public List<UserModel> getAllActiveEmployees(){
        List<UserModel> employees = new ArrayList<>();

        try{
            connection = ConnectionManager.getConnection();

            Statement statement = connection.createStatement();
            final String SQL_QUERY = "SELECT * FROM employee WHERE is_user_active = true";
            ResultSet resultSet = statement.executeQuery(SQL_QUERY);

            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String userName = resultSet.getString(4);
                String password = resultSet.getString(5);
                String employeeType = resultSet.getString(6);
                boolean isUserActive = resultSet.getBoolean(7);

                employees.add(new UserModel(id, firstName, lastName, userName, password, employeeType, isUserActive));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return employees;
    }

    public void updatePassword(String logInName, String firstNewPassword){

        try{
            connection = ConnectionManager.getConnection();
            final String SQL_QUERY = "UPDATE employee SET employee_password = ? WHERE employee_username ='" + logInName + "';";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY);
            preparedStatement.setString(1, firstNewPassword);
            preparedStatement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
