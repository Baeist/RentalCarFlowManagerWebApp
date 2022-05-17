package com.example.rentalcarflowmanagerwebapp.repository;

import com.example.rentalcarflowmanagerwebapp.model.Car;
import com.example.rentalcarflowmanagerwebapp.model.User;
import com.example.rentalcarflowmanagerwebapp.utility.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarRepository {

    Connection connection;
    Car car = new Car();


    public List<Car> getAllCars() {

        List<Car> allCars = new ArrayList<>();

        try {
            connection = ConnectionManager.getConnection();

            Statement statement = connection.createStatement();
            final String SQL_QUERY = "SELECT * FROM car;";
            ResultSet rs = statement.executeQuery(SQL_QUERY);

            while (rs.next()) {

                if (rs.next()) {
                    car.setCarID(rs.getInt(1));
                    car.setChassisNumber(rs.getString(2));
                    car.setColor(rs.getString(3));
                    car.setManufacturer(rs.getString(4));
                    car.setCarType(rs.getString(5));
                    car.setCarName(rs.getString(6));
                    car.setGearLevel(rs.getInt(7));
                    car.setSteelPriceDKK(rs.getDouble(8));
                    car.setRegistrationFeeDKK(rs.getDouble(9));
                    car.setCO2EmissionPerKM(rs.getDouble(10));
                    car.setCarPricePerMonthDKK(rs.getDouble(11));

                    return allCars;
                }
            }
        }
        catch(Exception e){
                e.printStackTrace();
            }

        return null;
    }

    public void updateCarInfo(int carID, String chassisNumber, String color, String manufacturer, String carType,
                              String carName, int gearLevel, double steelPriceDKK, double registrationFeeDKK,
                              double CO2EmissionPerKM, double carPricePerMonthDKK) {

        try {
            connection = ConnectionManager.getConnection();
            final String SQL_QUERY = "UPDATE car SET chassis_number = ?, color = ?, car_manufactorer = ?, car_type = ?," +
                    " car_name = ?, car_gear_level = ?, car_steel_price_dkk = ?, car_registration_fee_dkk = ?" +
                    "car_co2_emission_per_km = ?, car_rental_price_per_month_dkk = ? WHERE car_id =" + carID + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY);
            preparedStatement.setString(1, chassisNumber);
            preparedStatement.setString(2, color);
            preparedStatement.setString(3, manufacturer);
            preparedStatement.setString(4, carType);
            preparedStatement.setString(5, carName);
            preparedStatement.setInt(6, gearLevel);
            preparedStatement.setDouble(7, steelPriceDKK);
            preparedStatement.setDouble(8, registrationFeeDKK);
            preparedStatement.setDouble(9, CO2EmissionPerKM);
            preparedStatement.setDouble(10, carPricePerMonthDKK);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Car getCarFromCarID(int carID){

        try{
            connection = ConnectionManager.getConnection();

            final String SQL_QUERY = "SELECT * FROM car WHERE carID = ?";
            PreparedStatement ps = connection.prepareStatement(SQL_QUERY);
            ps.setInt(1, carID);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                car.setCarID(rs.getInt(1));
                car.setChassisNumber(rs.getString(2));
                car.setColor(rs.getString(3));
                car.setManufacturer(rs.getString(4));
                car.setCarType(rs.getString(5));
                car.setCarName(rs.getString(6));
                car.setGearLevel(rs.getInt(7));
                car.setSteelPriceDKK(rs.getDouble(8));
                car.setRegistrationFeeDKK(rs.getDouble(9));
                car.setCO2EmissionPerKM(rs.getDouble(10));
                car.setCarPricePerMonthDKK(rs.getDouble(11));

                return car;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void enterNewCar(String chassisNumber, String color, String manufacturer, String carType,
                            String carName, int gearLevel, double steelPriceDKK, double registrationFeeDKK,
                            double CO2EmissionPerKM, double carPricePerMonthDKK) {

        try {
            connection = ConnectionManager.getConnection();
            final String SQL_QUERY = "INSERT INTO car(car_id, chassis_number, color, car_manufactorer, car_type, car_name, car_gear_level," +
                    " car_steel_price_dkk, car_registration_fee_dkk, + car_co2_emission_per_km," +
                    " car_rental_price_per_month_dkk) VALUES(default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY);
            preparedStatement.setString(1, chassisNumber);
            preparedStatement.setString(2, color);
            preparedStatement.setString(3, manufacturer);
            preparedStatement.setString(4, carType);
            preparedStatement.setString(5, carName);
            preparedStatement.setInt(6, gearLevel);
            preparedStatement.setDouble(7, steelPriceDKK);
            preparedStatement.setDouble(8, registrationFeeDKK);
            preparedStatement.setDouble(9, CO2EmissionPerKM);
            preparedStatement.setDouble(10, carPricePerMonthDKK);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
