package com.example.rentalcarflowmanagerwebapp.repository;

import com.example.rentalcarflowmanagerwebapp.model.Status;
import com.example.rentalcarflowmanagerwebapp.utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

// Lars
@Repository
public class StatusRepository {

    Connection connection = ConnectionManager.getConnection();

    public Status getStatusFromCarID(int carID){

        try{

            Status status = new Status();
            final String SQL = "SELECT * FROM status WHERE car_id = ?";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setInt(1, carID);
            ResultSet rs =  ps.executeQuery();

            while(rs.next()){
                status.setStatusID(rs.getInt(1));
                status.setCarID(rs.getInt(2));
                status.setStatusDescription(rs.getString(3));
                status.setStartDate(rs.getDate(4).toLocalDate());

                if(rs.getDate(5) != null)
                status.setEndDate(rs.getDate(5).toLocalDate());

                return status;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public void editStatus(int statusID, int carID, String statusDescription, LocalDate startDate, LocalDate endDate) {

        try {

            final String SQL_QUERY = "UPDATE status SET car_id = ?, status_description = ?, status_start_date = ?, expected_status_end_date = ? WHERE status_id =" + statusID + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY);

            preparedStatement.setInt(1, carID);
            preparedStatement.setString(2, statusDescription);

            Date sqlStartDate = Date.valueOf(startDate);
            Date sqlEndDate = Date.valueOf(endDate);

            preparedStatement.setDate(3, sqlStartDate);
            preparedStatement.setDate(4, sqlEndDate);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void newStatus(int carID, String statusDescription, LocalDate startDate, LocalDate endDate) {

        try {

            final String SQL_QUERY = "INSERT INTO status( status_id, car_id, status_description, status_start_date, expected_status_end_date)" +
                    " VALUES(default , ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY);
            preparedStatement.setInt(1, carID);
            preparedStatement.setString(2, statusDescription);

            Date sqlStartDate = Date.valueOf(startDate);
            Date sqlEndDate = Date.valueOf(endDate);

            preparedStatement.setDate(3, sqlStartDate);
            preparedStatement.setDate(4, sqlEndDate);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
