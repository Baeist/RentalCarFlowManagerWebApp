package com.example.rentalcarflowmanagerwebapp.repository;

import com.example.rentalcarflowmanagerwebapp.model.Lease;
import com.example.rentalcarflowmanagerwebapp.utility.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Timer;
import java.time.*;

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
    public void updateLease(Lease newLease, int leaseID){
        final String SQL =  "UPDATE lease " +
                            "SET contract_id = ?, car_id = ?, lease_start_date = ?, lease_period_number_of_days = ?" +
                            "WHERE leaseID = ?;";

        try {
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, newLease.getContractID());
            ps.setInt(2, newLease.getCarID());

            Date sqlDate = Date.valueOf(newLease.getStartDate());

            ps.setDate(3, sqlDate);
            ps.setInt(4, newLease.getLeasePeriodDays());
            ps.setInt(5, leaseID);

            ps.execute();

        } catch (SQLException e) {
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
        Lease lease = new Lease();

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

            lease = new Lease(leaseID, startDate, contractID, leasePeriodDays, carID);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lease;
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

}
