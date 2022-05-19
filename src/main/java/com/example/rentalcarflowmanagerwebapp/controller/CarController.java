package com.example.rentalcarflowmanagerwebapp.controller;

import com.example.rentalcarflowmanagerwebapp.model.Car;
import com.example.rentalcarflowmanagerwebapp.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    model.addAttribute("cars", cars);

    return "car_stats";
  }
  @GetMapping("/bilstatistik/ledige")
  public String bilStatistikLedige(Model model){
    ArrayList<Car> cars = carService.availableCars();
    model.addAttribute("cars", cars);
    return "car_stats";
  }

  @GetMapping("/all_cars")
  public String showAllCars(Model model){

    ArrayList<Car> cars = carService.getAllCars();
    model.addAttribute("cars", cars);

    return "car_stats";
  }

  @GetMapping("/create_car")
    public String createCar(){

      return "/forms/car_form";
    }

    @PostMapping("/forms/create_car")
  public String carCreated(@RequestParam("chassis_number") String chassisNumber, @RequestParam("car_color")  String color, @RequestParam("manufacturer")  String manufacturer,
                           @RequestParam("car_type") String carType, @RequestParam("car_name") String carName, @RequestParam("gear_level")  int gearLevel,
                           @RequestParam("steel_price") double steelPriceDKK, @RequestParam("registration_fee")  double registrationFeeDKK,
                           @RequestParam("CO2_emission") double CO2EmissionPerKM, @RequestParam("price_per_month") double carPricePerMonthDKK){

    carService.enterNewCar(chassisNumber, color, manufacturer, carType, carName, gearLevel,
            steelPriceDKK, registrationFeeDKK, CO2EmissionPerKM, carPricePerMonthDKK);

    return "redirect:/all_cars";
    }


  }

