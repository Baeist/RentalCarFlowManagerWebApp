package com.example.rentalcarflowmanagerwebapp.controller;

import com.example.rentalcarflowmanagerwebapp.model.Car;
import com.example.rentalcarflowmanagerwebapp.repository.StatusRepository;
import com.example.rentalcarflowmanagerwebapp.service.CarService;
import com.example.rentalcarflowmanagerwebapp.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class CarController {
  private CarService carService;
  private StatusService statusService;

  public CarController(CarService carService, StatusService statusService){
    this.statusService = statusService;
    this.carService = carService;
  }

  @GetMapping("/car_stats")
  public String carStatistics(Model model){

    model.addAttribute("location", "economy");

    ArrayList<Car> carsAvailable = carService.availableCars();
    model.addAttribute("available_cars", carsAvailable);
    ArrayList<Car> carsLeased = carService.rentedOutCars();
    model.addAttribute("leased_out_cars", carsLeased);
    double monthlyEarnings = carsLeased.stream().map(Car::getCarPricePerMonthDKK).reduce(0.0,(subtotal,element) -> subtotal + element);
    model.addAttribute("monthly_earnings", monthlyEarnings);

    ArrayList<Integer> availableCarID = carService.getAvailableCarID();
    model.addAttribute("available_status_and_days", statusService.getStatusAndDaysLeft(availableCarID));

    ArrayList<Integer> leasedCarID = carService.getLeasedCarID();
    model.addAttribute("leased_status_and_days", statusService.getStatusAndDaysLeft(leasedCarID));

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
                           @RequestParam("CO2_emission") double CO2EmissionPerKM, @RequestParam("price_per_month") double carPricePerMonthDKK, RedirectAttributes ra){


    if(carService.findCarFromChassisNumber(chassisNumber) != null){

      ra.addFlashAttribute("carExists", "Den oprettede bil eksisterede allerede i databasen.");

      return "redirect:/create_car";
      }

    carService.enterNewCar(chassisNumber, color, manufacturer, carType, carName, gearLevel,
            steelPriceDKK, registrationFeeDKK, CO2EmissionPerKM, carPricePerMonthDKK);

    return "redirect:/car_stats";
    }

    @GetMapping("/edit_car/{chassis_number}")
    public String editCar(@PathVariable("chassis_number") String chassisNumber, HttpSession session, Model model){

    model.addAttribute("carToEdit", carService.findCarFromChassisNumber(chassisNumber));
    session.setAttribute("isEditCar", true);

    ArrayList<Car> carsAvailable = carService.availableCars();
    model.addAttribute("available_cars", carsAvailable);
    ArrayList<Car> carsLeased = carService.rentedOutCars();
    model.addAttribute("leased_out_cars", carsLeased);

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

      ArrayList<Car> carsAvailable = carService.availableCars();
      model.addAttribute("available_cars", carsAvailable);
      ArrayList<Car> carsLeased = carService.rentedOutCars();
      model.addAttribute("leased_out_cars", carsLeased);
      double monthlyEarnings = carsLeased.stream().map(Car::getCarPricePerMonthDKK).reduce(0.0,(subtotal,element) -> subtotal + element);
      model.addAttribute("monthly_earnings", monthlyEarnings);

    return "car_stats";
    }

    @GetMapping("/regret_edit_car")
    public String regretEditCar(HttpSession session, Model model){

    session.removeAttribute("isEditCar");

      ArrayList<Car> carsAvailable = carService.availableCars();
      model.addAttribute("available_cars", carsAvailable);
      ArrayList<Car> carsLeased = carService.rentedOutCars();
      model.addAttribute("leased_out_cars", carsLeased);
      double monthlyEarnings = carsLeased.stream().map(Car::getCarPricePerMonthDKK).reduce(0.0,(subtotal,element) -> subtotal + element);
      model.addAttribute("monthly_earnings", monthlyEarnings);

    return "car_stats";
    }

    @GetMapping("/forms/status_form")
    public String carStatusForm(Model model, HttpSession session){

      model.addAttribute("car_id", session.getAttribute("car_id"));
      // model.addAttribute("status", statusService.getStatusFromCarID((int)session.getAttribute("car_id")));

    return "/forms/status_form";
    }

    @PostMapping("/forms/status_form")
  public String carStatusForm(@RequestParam("car_id") int carID, HttpSession session, Model model){
      session.setAttribute("car_id", carID);
      model.addAttribute("car_id", session.getAttribute("car_id"));
    return "/forms/status_form";
    }
  @PostMapping("/forms/status_form/edit")
  public String carStatusForm(@RequestParam("status_id") int statusID, @RequestParam("car_id") int carID, HttpSession session, Model model){
    session.setAttribute("status_id", statusID);
    session.setAttribute("has_status", true);
    session.setAttribute("car_id", carID);
    model.addAttribute("car_id", session.getAttribute("car_id"));
    return "/forms/status_form";
  }
  @PostMapping("/forms/edit_status")
  public String editStatus(@RequestParam("status_description") String statusDescription,
                          @RequestParam("start_date") String startDate, @RequestParam("days_left") int daysLeft, HttpSession session){

    int carID = (int)session.getAttribute("car_id");
    int statusID = (int)session.getAttribute("status_id");

    statusService.editStatus(statusID, carID, statusDescription, startDate, daysLeft);

    return "redirect:/car_stats";
  }

    @PostMapping("/forms/confirm_status_form")
  public String newStatus(@RequestParam("status_description") String statusDescription,
                             @RequestParam("start_date") String startDate, @RequestParam("days_left") int daysLeft, HttpSession session){

    int carID = (int)session.getAttribute("car_id");


    statusService.newStatus(carID, statusDescription, startDate, daysLeft);

    return "redirect:/car_stats";
    }

  }

