package com.example.rentalcarflowmanagerwebapp.repository;

import com.example.rentalcarflowmanagerwebapp.model.Contract;
import com.example.rentalcarflowmanagerwebapp.model.DamageReport;
import com.example.rentalcarflowmanagerwebapp.model.Lease;
import com.example.rentalcarflowmanagerwebapp.utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Klasse lavet af Arjaco
@Repository
public class DamageReportRepository {

  public List<Integer> getAllContractIds() {
    List<Integer> listOfContractIds = new ArrayList<>();
    try {
      Connection connection = ConnectionManager.getConnection();
      String SQL_QUERY = "SELECT contract_id FROM contract";
      PreparedStatement ps = connection.prepareStatement(SQL_QUERY);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        int contract_id = rs.getInt("contract_id");
        listOfContractIds.add(contract_id);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return listOfContractIds;
  }

  public List<Lease> getAllLeases() {
    List<Lease> listOfLeases = new ArrayList<>();
    try {
      Connection connection = ConnectionManager.getConnection();
      String SQL_QUERY = "SELECT contract_id, car_id FROM lease";
      PreparedStatement ps = connection.prepareStatement(SQL_QUERY);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        int contract_id = rs.getInt("contract_id");
        int car_id = rs.getInt("car_id");
        Lease lm = new Lease(0, null, contract_id, 0, car_id);
        lm.setContractID(contract_id);
        lm.setCarID(car_id);
        listOfLeases.add(lm);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return listOfLeases;
  }

  public List<DamageReport> getAllDamageReports() {
    List<DamageReport> listOfDamageReports = new ArrayList<>();
    try {
      Connection connection = ConnectionManager.getConnection();
      String SQL_QUERY = "SELECT * FROM damage_report ORDER BY car_id";
      PreparedStatement ps = connection.prepareStatement(SQL_QUERY);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        int car_id = rs.getInt("car_id");
        int contract_id = rs.getInt("contract_id");
        int damage_report_id = rs.getInt("damage_report_id");
        String description = rs.getString("description");
        int damage_price_dkk = rs.getInt("damage_price_dkk");
        String garage_name = rs.getString("garage_name");
        listOfDamageReports.add(new DamageReport(damage_report_id, description, damage_price_dkk, garage_name, contract_id, car_id));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return listOfDamageReports;
  }

  public DamageReport getDamageReport(int carId, int contractId) {
    DamageReport damageReport = null;
    try {
      Connection connection = ConnectionManager.getConnection();
      String SQL_QUERY = "SELECT * FROM damage_report WHERE car_id = ? AND contract_id = ? ORDER BY car_id";
      PreparedStatement ps = connection.prepareStatement(SQL_QUERY);
      ps.setInt(1, carId);
      ps.setInt(2, contractId);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        int damage_report_id = rs.getInt("damage_report_id");
        String description = rs.getString("description");
        int damage_price_dkk = rs.getInt("damage_price_dkk");
        String garage_name = rs.getString("garage_name");
        damageReport = new DamageReport(damage_report_id, description, damage_price_dkk, garage_name, contractId, carId);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return damageReport;
  }

  public void createDamageReport(int contract_id, int carId, String description, int damage_price_dkk, String garage_name) {
    try {
      Connection connection = ConnectionManager.getConnection();
      String SQL_QUERY = "INSERT INTO damage_report (contract_id, car_id, description, damage_price_dkk, garage_name) " +
          "VALUES (?, ?, ?, ?, ?)";
      PreparedStatement ps = connection.prepareStatement(SQL_QUERY);
      ps.setInt(1, contract_id);
      ps.setInt(2, carId);
      ps.setString(3, description);
      ps.setInt(4, damage_price_dkk);
      ps.setString(5, garage_name);
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void editDamageReport(int damage_report_id, String description, int damage_price_dkk, String garage_name) {
    try {
      Connection connection = ConnectionManager.getConnection();
      String SQL_QUERY = "UPDATE damage_report SET description = ?, damage_price_dkk = ?, garage_name = ? WHERE damage_report_id = ?";
      PreparedStatement ps = connection.prepareStatement(SQL_QUERY);
      ps.setString(1, description);
      ps.setInt(2, damage_price_dkk);
      ps.setString(3, garage_name);
      ps.setInt(4, damage_report_id);
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deleteDamageReport(int damage_report_id) {
    try {
      Connection connection = ConnectionManager.getConnection();
      String SQL_QUERY = "DELETE FROM damage_report WHERE damage_report_id = ?";
      PreparedStatement ps = connection.prepareStatement(SQL_QUERY);
      ps.setInt(1, damage_report_id);
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
