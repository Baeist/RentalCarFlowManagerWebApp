package com.example.rentalcarflowmanagerwebapp.service;

import com.example.rentalcarflowmanagerwebapp.controller.CarController;
import com.example.rentalcarflowmanagerwebapp.model.Car;
import com.example.rentalcarflowmanagerwebapp.repository.CarRepository;
import com.example.rentalcarflowmanagerwebapp.repository.LeaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

// Valdemar
@Service
public class CarService {

  private CarRepository carRepository;
  private CarController carController;

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
  public void deleteCar(String chassisNumber){

    carRepository.deleteCar(chassisNumber);
  }

  public ArrayList<Integer> getAvailableCarID(){

    ArrayList<Integer> availableCarID = new ArrayList<>();

    ArrayList<Car> allCars = availableCars();

    for(int i = 0; i < allCars.size(); i++){

      int carID = allCars.get(i).getCarID();

      availableCarID.add(carID);
    }

    return availableCarID;
  }
  public ArrayList<Integer> getLeasedCarID(){

    ArrayList<Integer> allCarID = new ArrayList<>();

    ArrayList<Car> allCars = rentedOutCars();

    for(int i = 0; i < allCars.size(); i++){

      int carID = allCars.get(i).getCarID();

      allCarID.add(carID);
    }
    return allCarID;
  }
}