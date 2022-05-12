package com.example.rentalcarflowmanagerwebapp.repository;

import com.example.rentalcarflowmanagerwebapp.model.Contract;
import com.example.rentalcarflowmanagerwebapp.utility.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContractRepository {

    private Connection con = ConnectionManager.getConnection();

    public void saveContract(Contract contract){

        final String SQL =  "INSERT INTO Contract(employee_id, customer_id)" +
                            "VALUES (?, ?);";

        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, contract.getEmployeeID());
            ps.setInt(2, contract.getCustomerID());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Contract getContract(int contractID){
        Contract contract = new Contract();

        final String SQL =  "SELECT * FROM contract WHERE contract_id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, contractID);

            ResultSet resultSet = ps.executeQuery();

            resultSet.next();
            int contractIDSQL = resultSet.getInt(1);
            int customerID = resultSet.getInt(2);
            int employeeID = resultSet.getInt(3);

            contract = new Contract(contractIDSQL ,customerID, employeeID);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return contract;
    }

    public ArrayList<Contract> getAllContract(){
        ArrayList<Contract> contracts = new ArrayList<>();

        final String SQL =  "SELECT * FROM contract;";

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

    public void deleteContract(int contractID){

        final String SQL =  "DELETE FROM contract WHERE contract_id = ?;";

        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, contractID);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



}
