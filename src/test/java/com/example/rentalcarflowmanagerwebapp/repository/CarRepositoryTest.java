package com.example.rentalcarflowmanagerwebapp.repository;

import com.example.rentalcarflowmanagerwebapp.model.Car;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Valdemar
public class CarRepositoryTest {

  CarRepository cr = new CarRepository();

  @Test
  void rentedOutCars(){
    final int ALL_RENTED_OUT_CARS = 3;
    ArrayList<Car> rentedOutCarsTest = cr.rentedOutCars();
    for (int i = 0; i < rentedOutCarsTest.size(); i++){
      assertNotNull(rentedOutCarsTest.get(i));

    }
    assertEquals(ALL_RENTED_OUT_CARS, rentedOutCarsTest.size());
  }
  @Test
  void availableCars(){
    final int ALL_AVAILABLE_CARS = 16;
    ArrayList<Car> availableCarsTest = cr.availableCars();
    for (int i = 0; i < availableCarsTest.size(); i++){
    assertNotNull(availableCarsTest.size());
    }
    assertEquals(ALL_AVAILABLE_CARS, availableCarsTest.size());
  }
 /* @Test
  void enterNewCar(){
     String testChassisNumber = "321frt123frgqwert";
     String testColor = "bloo";
     String testManufacturer = "Ford";
     String testCarType = "varevogn";
     String testCarName = "Transit";
     int testGearLevel = 1;
     double testSteelPriceDKK = 100000;
     double testRegistrationFeeDKK = 15000;
     double testCO2EmissionPerKM = 4.69;
     double testCarPricePerMonthDKK = 42069;
     int expectedCarID = 23;
     String expectedTestCarName = "Transit";

     cr.enterNewCar(testChassisNumber,testColor,testManufacturer,testCarType,testCarName,testGearLevel,testSteelPriceDKK,testRegistrationFeeDKK,testCO2EmissionPerKM,testCarPricePerMonthDKK);

     Car minFordTransit = cr.findCarFromChassisNumber("321frt123frgqwert");

     int actualCarID = minFordTransit.getCarID();
     String actualTestCarName = minFordTransit.getCarName();
     assertEquals(expectedCarID,actualCarID);
     assertEquals(expectedTestCarName,actualTestCarName);
  }
*/
}


