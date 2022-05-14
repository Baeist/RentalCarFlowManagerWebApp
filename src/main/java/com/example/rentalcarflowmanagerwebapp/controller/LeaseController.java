package com.example.rentalcarflowmanagerwebapp.controller;


import com.example.rentalcarflowmanagerwebapp.model.Contract;
import com.example.rentalcarflowmanagerwebapp.model.Lease;
import com.example.rentalcarflowmanagerwebapp.service.ContractService;
import com.example.rentalcarflowmanagerwebapp.service.LeaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @GetMapping("/dashboard/lease/delete/{leaseID}")
    public String deleteLease(@PathVariable("leaseID") int leaseID){
        leaseService.deleteLease(leaseID);
        return "redirect:/dashboard/lease";
    }

    @GetMapping("/dashboard/lease/edit/{leaseID}")
    public String editLease(@PathVariable("leaseID") int leaseID){


        return "redirect:/dashboard/lease";
    }

}
