package com.example.rentalcarflowmanagerwebapp.controller;

import com.example.rentalcarflowmanagerwebapp.model.Car;
import com.example.rentalcarflowmanagerwebapp.model.Contract;
import com.example.rentalcarflowmanagerwebapp.model.Lease;
import com.example.rentalcarflowmanagerwebapp.repository.CarRepository;
import com.example.rentalcarflowmanagerwebapp.service.CarService;
import com.example.rentalcarflowmanagerwebapp.service.ContractService;
import com.example.rentalcarflowmanagerwebapp.service.LeaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Tobias
@Controller
public class LeaseController {

    private LeaseService leaseService;
    private ContractService contractService;
    private CarService carService;

    public LeaseController(LeaseService leaseService, ContractService contractService, CarService carService) {
        this.leaseService = leaseService;
        this.contractService = contractService;
        this.carService = carService;
    }

    @GetMapping("/lease")
    public String dashboardLease(Model model){
        List<Lease> allLeases = leaseService.getAllLeases();
        List<Contract> allContracts = contractService.GetAllContracts();

        model.addAttribute("allLeases", allLeases);
        model.addAttribute("allContracts", allContracts);

        //for top menu
        model.addAttribute("location", "lease");

        return "lease_dashboard";
    }


    @PostMapping("/lease/save")
    public String saveLease(@RequestParam("leaseID") int leaseID,
                            @RequestParam("startDate") String startDate,
                            @RequestParam("contractID") int contractID,
                            @RequestParam("leasePeriodDays") int periodDays,
                            @RequestParam("carID") int carID
                            ){




        LocalDate startLocalDate = leaseService.convertStringtoLocalDate(startDate);

        Lease lease = new Lease(leaseID, startLocalDate, contractID, periodDays, carID);
        leaseService.saveLease(lease);

        return "redirect:/lease";
    }

    @GetMapping("/lease/delete/{leaseID}")
    public String deleteLease(@PathVariable("leaseID") int leaseID){
        leaseService.deleteLease(leaseID);
        return "redirect:/lease";
    }

    @GetMapping("/lease/edit/{contractID}/{leaseID}/{leaseStartDate}/{totalDays}/{carID}")
    public String editLease(@PathVariable("leaseID") int leaseID,
                            @PathVariable("leaseStartDate") String date,
                            @PathVariable("totalDays") int totalDays,
                            @PathVariable("carID") int carID,
                            @PathVariable("contractID") int contractID,
                            Model model){

        List<Contract> allContracts = contractService.GetAllContracts();
        List<Car> allAvailableCars = carService.availableCars();

        model.addAttribute("allContracts", allContracts);
        model.addAttribute("allAvailableCars", allAvailableCars);

        model.addAttribute("pageTitle", "Ændrer lease: " + leaseID);

        model.addAttribute("leaseID", leaseID);
        model.addAttribute("leaseStartDate", date);
        model.addAttribute("totalDays", totalDays);
        model.addAttribute("carID", carID);
        model.addAttribute("contractID", contractID);

        return "Forms/lease_form";
    }

    @GetMapping("/lease/create")
    public String createLease(Model model){
        model.addAttribute("pageTitle", "Opret lejeaftale");

        List<Contract> allContracts = contractService.GetAllContracts();
        List<Car> allAvailableCars = carService.availableCars();

        model.addAttribute("allContracts", allContracts);
        model.addAttribute("allAvailableCars", allAvailableCars);

        // gives none existing id
        model.addAttribute("leaseID", -1);

        return "Forms/lease_form";
    }

}
