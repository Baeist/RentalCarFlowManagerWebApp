package com.example.rentalcarflowmanagerwebapp.repository;

import com.example.rentalcarflowmanagerwebapp.model.Lease;
import com.example.rentalcarflowmanagerwebapp.utility.ConnectionManager;
import org.springframework.stereotype.Repository;
import com.example.rentalcarflowmanagerwebapp.model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.time.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



@Repository
public class LeaseRepository {

    private Connection con = ConnectionManager.getConnection();

    public void saveLease(Lease lease){
        final String SQL =  "INSERT INTO lease (contract_id, car_id, lease_start_date, lease_period_number_of_days)" +
                            "VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, lease.getContractID());
            ps.setInt(2, lease.getCarID());

            Date sqlDate = Date.valueOf(lease.getStartDate());

            ps.setDate(3, sqlDate);
            ps.setInt(4, lease.getLeasePeriodDays());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void saveLeases(ArrayList<Lease> leases){
        for (int i = 0; i < leases.size(); i++) {
            saveLease(leases.get(i));
        }
    }
    public void updateLease(Lease newLease){
        final String SQL =  "UPDATE lease " +
                            "SET contract_id = ?, car_id = ?, lease_start_date = ?, lease_period_number_of_days = ? " +
                            "WHERE lease_id = ?;";

        try {
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, newLease.getContractID());
            ps.setInt(2, newLease.getCarID());

            Date sqlDate = Date.valueOf(newLease.getStartDate());

            ps.setDate(3, sqlDate);
            ps.setInt(4, newLease.getLeasePeriodDays());
            ps.setInt(5, newLease.getLeaseID());

            ps.execute();

        } catch (SQLException e) {

            System.out.println(e.getSQLState());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());

            e.printStackTrace();
        }
    }
    public void deleteLease(int leaseID){
        final String SQL = "DELETE FROM lease WHERE lease_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, leaseID);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
    public Lease getLease(int leaseID){


        final String SQL =  "SELECT * FROM lease " +
                            "WHERE leaseID = ?;";

        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, leaseID);

            ResultSet resultSet = ps.executeQuery();

            resultSet.next();

            int contractID = resultSet.getInt(2);
            int carID = resultSet.getInt(3);
            LocalDate startDate = resultSet.getDate(4).toLocalDate();
            int leasePeriodDays = resultSet.getInt(5);

            Lease lease = new Lease(leaseID, startDate, contractID, leasePeriodDays, carID);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public ArrayList<Lease> getAllLease(){
        ArrayList<Lease> leases = new ArrayList<>();

        final String SQL =  "SELECT * FROM lease;";

        try {
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet resultSet = ps.executeQuery();


            while (resultSet.next()){
                int leaseID = resultSet.getInt(1);
                int contractID = resultSet.getInt(2);
                int carID = resultSet.getInt(3);
                LocalDate startDate = resultSet.getDate(4).toLocalDate();
                int leasePeriodDays = resultSet.getInt(5);

                Lease lease = new Lease(leaseID, startDate, contractID, leasePeriodDays, carID);
                leases.add(lease);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return leases;
    }
    public boolean leaseExist(int leaseID) {
        boolean leaseExist = false;

        final String SQL =  "SELECT * FROM lease " +
                "WHERE lease_ID = ?;";

        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, leaseID);

            ResultSet resultSet = ps.executeQuery();


            if (resultSet.next()){
                leaseExist = true;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return leaseExist;
    }







  public static ArrayList<Car> seUdlejedeBiler(){
    try{
    ArrayList<Car> biler = new ArrayList<>();
    String queryString = "SELECT * FROM car WHERE car_id IN (SELECT car_id FROM udlejet)";
    PreparedStatement query = con.prepareStatement(queryString);
      ResultSet rs = query.executeQuery();
      while (rs.next()){
        Car car = new Car(
            rs.getInt("car_id"),
            rs.getString("chassis_number"),
            rs.getString("color"),
            rs.getString("car_manufactorer"),
            rs.getString("car_type"),
            rs.getString("car_name"),
            rs.getDouble("car_rental_price_per_month_dkk")
        );
        biler.add(car);
      }
      return biler;
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    return null;
  }
  public static ArrayList<Car> seLedigeBiler(){
    try{
      ArrayList<Car> biler = new ArrayList<>();
      String queryString = "SELECT * FROM car WHERE car_id NOT IN (SELECT car_id FROM udlejet)";
      PreparedStatement query = con.prepareStatement(queryString);
      ResultSet rs = query.executeQuery();
      while (rs.next()){
        Car car = new Car(
            rs.getInt("car_id"),
            rs.getString("chassis_number"),
            rs.getString("color"),
            rs.getString("car_manufactorer"),
            rs.getString("car_type"),
            rs.getString("car_name"),
            rs.getDouble("car_rental_price_per_month_dkk")
        );
        biler.add(car);
      }
      return biler;
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    return null;
  }
}
