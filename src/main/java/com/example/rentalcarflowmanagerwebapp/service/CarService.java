package com.example.rentalcarflowmanagerwebapp.service;

import com.example.rentalcarflowmanagerwebapp.model.Car;
import com.example.rentalcarflowmanagerwebapp.repository.CarRepository;
import com.example.rentalcarflowmanagerwebapp.repository.LeaseRepository;

import java.util.ArrayList;

public class CarService {

  private CarRepository carRepository;

  public CarService(CarRepository carRepository){
    this.carRepository = carRepository;
  }

  public ArrayList<Car> rentedOutCars(){

    return carRepository.rentedOutCars();


  }

  public ArrayList<Car> availableCars(){
    return carRepository.availableCars();
  }
}
