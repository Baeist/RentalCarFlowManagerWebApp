package com.example.rentalcarflowmanagerwebapp.repository;

import com.example.rentalcarflowmanagerwebapp.model.DamageReportModel;
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
  public List<DamageReportModel> getDamageReport(int carId, int contractId) {
    List<DamageReportModel> listOfDamages = new ArrayList<>();
    try {
    Connection connection = ConnectionManager.getConnection();
    String SQL_QUERY = "SELECT * FROM damage_report WHERE car_id = ? AND contract_id = ? ORDER BY car_id";
      PreparedStatement ps = connection.prepareStatement(SQL_QUERY);
      ps.setInt(1, carId);
      ps.setInt(2, contractId);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        int car_id = rs.getInt("car_id");
        int contract_id = rs.getInt("contract_id");
        int damage_report_id = rs.getInt("damage_report_id");
        String description = rs.getString("description");
        int damage_price_dkk = rs.getInt("damage_price_dkk");
        String garage_name = rs.getString("garage_name");
        listOfDamages.add(new DamageReportModel(damage_report_id, description, damage_price_dkk, garage_name, contract_id, car_id));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return listOfDamages;
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
