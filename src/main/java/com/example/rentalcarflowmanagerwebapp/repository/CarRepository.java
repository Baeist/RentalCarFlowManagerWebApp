package com.example.rentalcarflowmanagerwebapp.repository;

import com.example.rentalcarflowmanagerwebapp.model.Car;
import com.example.rentalcarflowmanagerwebapp.utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {

  Connection con = ConnectionManager.getConnection();

    public ArrayList<Car> rentedOutCars() {
        try {
            ArrayList<Car> cars = new ArrayList<>();
            String queryString = "SELECT * FROM car WHERE car_id IN (SELECT car_id FROM (SELECT \n" +
                "    car_id\n" +
                "FROM\n" +
                "    lease\n" +
                "WHERE\n" +
                "    lease_start_date <= CURDATE()\n" +
                "        AND DATE_ADD(lease_start_date,\n" +
                "        INTERVAL lease_period_number_of_days DAY) > CURDATE()) AS rented_out_cars)";
            PreparedStatement query = con.prepareStatement(queryString);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                Car car = new Car(
                        rs.getInt("car_id"),
                        rs.getString("chassis_number"),
                        rs.getString("color"),
                        rs.getString("car_manufactorer"),
                        rs.getString("car_type"),
                        rs.getString("car_name"),
                        rs.getInt("car_gear_level"),
                        rs.getDouble("car_steel_price_dkk"),
                        rs.getDouble("car_registrations_fee_dkk"),
                        rs.getDouble("car_co2_emission_per_km"),
                        rs.getDouble("car_rental_price_per_month_dkk")
                );
                cars.add(car);
            }
            return cars;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public ArrayList<Car> availableCars() {
        try {
            ArrayList<Car> cars = new ArrayList<>();
            String queryString = "SELECT * FROM car WHERE car_id NOT IN (SELECT car_id FROM (SELECT \n" +
                "    car_id\n" +
                "FROM\n" +
                "    lease\n" +
                "WHERE\n" +
                "    lease_start_date <= CURDATE()\n" +
                "        AND DATE_ADD(lease_start_date,\n" +
                "        INTERVAL lease_period_number_of_days DAY) > CURDATE()) AS available_cars)";
            PreparedStatement query = con.prepareStatement(queryString);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                Car car = new Car(
                        rs.getInt("car_id"),
                        rs.getString("chassis_number"),
                        rs.getString("color"),
                        rs.getString("car_manufactorer"),
                        rs.getString("car_type"),
                        rs.getString("car_name"),
                        rs.getInt("car_gear_level"),
                        rs.getDouble("car_steel_price_dkk"),
                        rs.getDouble("car_registrations_fee_dkk"),
                        rs.getDouble("car_co2_emission_per_km"),
                        rs.getDouble("car_rental_price_per_month_dkk")
                );
                cars.add(car);
            }
            return cars;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

        public ArrayList<Car> getAllCars() {

            ArrayList<Car> allCars = new ArrayList<>();


            try {
                con = ConnectionManager.getConnection();

                Statement statement = con.createStatement();
                final String SQL_QUERY = "SELECT * FROM car;";
                ResultSet rs = statement.executeQuery(SQL_QUERY);

                while (rs.next()) {

                    Car car = new Car();

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

                        allCars.add(car);
                }
                return allCars;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        public void updateCarInfo(int carID, String chassisNumber, String color, String manufacturer, String carType,
                                  String carName, int gearLevel, double steelPriceDKK, double registrationFeeDKK,
                                  double CO2EmissionPerKM, double carPricePerMonthDKK) {

            try {
                con = ConnectionManager.getConnection();
                final String SQL_QUERY = "UPDATE car SET chassis_number = ?, color = ?, car_manufactorer = ?, car_type = ?," +
                        " car_name = ?, car_gear_level = ?, car_steel_price_dkk = ?, car_registration_fee_dkk = ?" +
                        "car_co2_emission_per_km = ?, car_rental_price_per_month_dkk = ? WHERE car_id =" + carID + ";";
                PreparedStatement preparedStatement = con.prepareStatement(SQL_QUERY);
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

        public Car getCarFromCarID(int carID) {

            try {
                con = ConnectionManager.getConnection();
                Car car = new Car();

                final String SQL_QUERY = "SELECT * FROM car WHERE carID = ?";
                PreparedStatement ps = con.prepareStatement(SQL_QUERY);
                ps.setInt(1, carID);
                ResultSet rs = ps.executeQuery();

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

                    return car;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public void enterNewCar(String chassisNumber, String color, String manufacturer, String carType,
                                String carName, int gearLevel, double steelPriceDKK, double registrationFeeDKK,
                                double CO2EmissionPerKM, double carPricePerMonthDKK) {

            try {
                con = ConnectionManager.getConnection();
                final String SQL_QUERY = "INSERT INTO car(car_id, chassis_number, color, car_manufactorer, car_type, car_name, car_gear_level," +
                        " car_steel_price_dkk, car_registration_fee_dkk, car_co2_emission_per_km," +
                        " car_rental_price_per_month_dkk) VALUES(default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = con.prepareStatement(SQL_QUERY);
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



