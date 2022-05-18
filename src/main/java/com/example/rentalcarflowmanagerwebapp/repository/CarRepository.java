package com.example.rentalcarflowmanagerwebapp.repository;

import com.example.rentalcarflowmanagerwebapp.model.Car;
import com.example.rentalcarflowmanagerwebapp.utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class CarRepository {

  Connection con = ConnectionManager.getConnection();

  public ArrayList<Car> rentedOutCars(){
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
  public ArrayList<Car> availableCars(){
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
