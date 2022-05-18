package com.example.rentalcarflowmanagerwebapp.controller;

import com.example.rentalcarflowmanagerwebapp.model.Car;
import com.example.rentalcarflowmanagerwebapp.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class CarController {
  private CarService carService;

  public CarController(CarService carService){
    this.carService = carService;
  }

  @GetMapping("/bilstatistik/udlejet")
  public String bilStatistik(Model model){
    ArrayList<Car> cars = carService.rentedOutCars();
    model.addAttribute("biler", cars);

    return "car_stats";
  }
  @GetMapping("/bilstatistik/ledige")
  public String bilStatistikLedige(Model model){
    ArrayList<Car> cars = carService.availableCars();
    model.addAttribute("biler", cars);
    return "car_stats";
  }
}
