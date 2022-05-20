package com.example.rentalcarflowmanagerwebapp.service;

import com.example.rentalcarflowmanagerwebapp.model.Car;
import com.example.rentalcarflowmanagerwebapp.repository.CarRepository;
import com.example.rentalcarflowmanagerwebapp.repository.LeaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

  private CarRepository carRepository;

  public CarService(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  public ArrayList<Car> rentedOutCars() {

    return carRepository.rentedOutCars();


  }

  public ArrayList<Car> getAllCars() {
    return carRepository.getAllCars();
  }

  public ArrayList<Car> availableCars() {
    return carRepository.availableCars();
  }

  public void enterNewCar(String chassisNumber, String color, String manufacturer, String carType, String carName, int gearLevel,
                          double steelPriceDKK, double registrationFeeDKK, double CO2EmissionPerKM, double carPricePerMonthDKK){

    carRepository.enterNewCar(chassisNumber, color, manufacturer, carType, carName, gearLevel,
            steelPriceDKK, registrationFeeDKK, CO2EmissionPerKM, carPricePerMonthDKK);
  }

  public Car findCarFromChassisNumber(String chassisNumber){
    return carRepository.findCarFromChassisNumber(chassisNumber);
  }

  public void updateCarInfo(int carID, String chassisNumber, String color, String manufacturer, String carType,
                            String carName, int gearLevel, double steelPriceDKK, double registrationFeeDKK,
                            double CO2EmissionPerKM, double carPricePerMonthDKK){
    carRepository.updateCarInfo(carID, chassisNumber, color, manufacturer, carType,
            carName, gearLevel, steelPriceDKK, registrationFeeDKK,
            CO2EmissionPerKM, carPricePerMonthDKK);
  }
}