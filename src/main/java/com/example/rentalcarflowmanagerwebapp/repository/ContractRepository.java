package com.example.rentalcarflowmanagerwebapp.repository;
import org.springframework.stereotype.Repository;

import com.example.rentalcarflowmanagerwebapp.model.Contract;
import com.example.rentalcarflowmanagerwebapp.utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Tobias
@Repository
public class ContractRepository {

    private Connection con = ConnectionManager.getConnection();

    public boolean saveContract(Contract contract){

        final String SQL =  "INSERT INTO Contract(employee_id, customer_id)" +
                            "VALUES (?, ?);";

        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, contract.getEmployeeID());
            ps.setInt(2, contract.getCustomerID());

            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("could not create contract");
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());

            return false;
        }
    }

    public ArrayList<Contract> getAllContract(){
        ArrayList<Contract> contracts = new ArrayList<>();

        final String SQL =  "SELECT * FROM contract ORDER BY contract_id DESC;";

        try {
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){

                int contractID = resultSet.getInt(1);
                int customerID = resultSet.getInt(2);
                int employeeID = resultSet.getInt(3);

                Contract contract = new Contract(contractID ,customerID, employeeID);

                contracts.add(contract);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }

    public boolean deleteContract(int contractID){

        final String SQL =  "DELETE FROM contract WHERE contract_id = ?;";

        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, contractID);

            ps.execute();

            return true;


        } catch (SQLException e) {
            System.out.println("Could not delete Contract");
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            return false;
        }


    }

    public int totalLeasesForContract(int contractID){
        int total = 0;

        final String SQL =  "SELECT lease_id from contract " +
                            "INNER JOIN lease " +
                            "ON lease.contract_id = contract.contract_id " +
                            "where contract.contract_id = ?;";

        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, contractID);


            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                total++;
            }


        } catch (SQLException e) {
            System.out.println("Could not delete Contract");
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            total = 0;

        }


        return total;
    }



    public ArrayList<Contract> getAllContractWithCars() {
        ArrayList<Contract> contracts = new ArrayList<>();

        final String SQL =  "SELECT * FROM contract WHERE contract.contract_id IN (SELECT contract_id FROM lease) " +
                            "ORDER BY contract_id DESC;";

        try {
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){

                int contractID = resultSet.getInt(1);
                int customerID = resultSet.getInt(2);
                int employeeID = resultSet.getInt(3);

                Contract contract = new Contract(contractID ,customerID, employeeID);

                contracts.add(contract);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }



}
