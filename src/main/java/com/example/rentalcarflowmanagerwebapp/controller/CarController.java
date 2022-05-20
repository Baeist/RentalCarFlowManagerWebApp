package com.example.rentalcarflowmanagerwebapp.controller;

import com.example.rentalcarflowmanagerwebapp.model.Car;
import com.example.rentalcarflowmanagerwebapp.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class CarController {
  private CarService carService;

  public CarController(CarService carService){
    this.carService = carService;
  }

  @GetMapping("/c")
  public String bilStatistik(Model model){

    return "car_stats";
  }
  @GetMapping("/car_stats")
  public String bilStatistikLedige(Model model){
    ArrayList<Car> carsAvailable = carService.availableCars();
    model.addAttribute("available_cars", carsAvailable);
    ArrayList<Car> carsLeased = carService.rentedOutCars();
    model.addAttribute("leased_out_cars", carsLeased);

    return "car_stats";
  }

  @GetMapping("/all_cars")
  public String showAllCars(Model model, HttpSession session){

    session.removeAttribute("isEditCar");
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

    @GetMapping("/edit_car/{chassis_number}")
    public String editCar(@PathVariable("chassis_number") String chassisNumber, HttpSession session, Model model){

    model.addAttribute("carToEdit", carService.findCarFromChassisNumber(chassisNumber));
    session.setAttribute("isEditCar", true);

    ArrayList<Car> cars = carService.getAllCars();
    model.addAttribute("cars", cars);

    return "car_stats";
    }
    @PostMapping("/edited_car")
    public String confirmCarEdit(@RequestParam("chassis_number") String chassisNumber, @RequestParam("color")  String color, @RequestParam("manufacturer")  String manufacturer,
                                 @RequestParam("type") String carType, @RequestParam("name") String carName, @RequestParam("gear_level")  int gearLevel,
                                 @RequestParam("steel_price") double steelPriceDKK, @RequestParam("registration_fee")  double registrationFeeDKK,
                                 @RequestParam("CO2_emission") double CO2EmissionPerKM, @RequestParam("price_per_month") double carPricePerMonthDKK, @RequestParam("car_id") int carID,
                                 HttpSession session, Model model){

      carService.updateCarInfo(carID, chassisNumber, color, manufacturer, carType,
              carName, gearLevel, steelPriceDKK, registrationFeeDKK,
      CO2EmissionPerKM, carPricePerMonthDKK);

      session.removeAttribute("isEditCar");
      ArrayList<Car> cars = carService.getAllCars();
      model.addAttribute("cars", cars);

    return "car_stats";
    }

  }
