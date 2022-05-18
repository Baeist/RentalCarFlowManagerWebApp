package com.example.rentalcarflowmanagerwebapp.controller;

import com.example.rentalcarflowmanagerwebapp.model.Contract;
import com.example.rentalcarflowmanagerwebapp.model.Lease;
import com.example.rentalcarflowmanagerwebapp.service.ContractService;
import com.example.rentalcarflowmanagerwebapp.service.LeaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class LeaseController {

    private LeaseService leaseService;
    private ContractService contractService;

    public LeaseController(LeaseService leaseService, ContractService contractService) {
        this.leaseService = leaseService;
        this.contractService = contractService;
    }

    @GetMapping("/dashboard/lease")
    public String dashboardLease(Model model){
        List<Lease> allLeases = leaseService.getAllLeases();
        List<Contract> allContracts = contractService.GetAllContracts();

        model.addAttribute("allLeases", allLeases);
        model.addAttribute("allContracts", allContracts);

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

        return "redirect:/dashboard/lease";
    }

    @GetMapping("/dashboard/lease/delete/{leaseID}")
    public String deleteLease(@PathVariable("leaseID") int leaseID){
        leaseService.deleteLease(leaseID);
        return "redirect:/dashboard/lease";
    }

    @GetMapping("/dashboard/lease/edit/{leaseID}")
    public String editLease(@PathVariable("leaseID") int leaseID, Model model){
        model.addAttribute("pageTitle", "Ændrer lease: " + leaseID);
        model.addAttribute("leaseID", leaseID);
        return "Forms/lease_form";
    }

    @GetMapping("/dashboard/lease/create")
    public String createLease(Model model){
        model.addAttribute("pageTitle", "Opret lejeaftale");

        // gives none existing id
        model.addAttribute("leaseID", -1);

        return "Forms/lease_form";
    }

}
