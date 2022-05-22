package com.example.rentalcarflowmanagerwebapp.controller;

import com.example.rentalcarflowmanagerwebapp.model.Contract;
import com.example.rentalcarflowmanagerwebapp.service.ContractService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContractController {

    ContractService contractService;

    public ContractController(ContractService contractService){
        this.contractService = contractService;
    }

    @PostMapping("create/contract/{employeeID}")
    public String createContract(@RequestParam("customerID") int customerID,
                                 @PathVariable("employeeID") int employeeID,
                                 RedirectAttributes ra){

        Contract contract = new Contract(-1, customerID, employeeID);

        boolean isCreated = contractService.createContract(contract);


        if (isCreated){
            String message = "Ny kontrakt oprettet";
            ra.addFlashAttribute("succes", message);
        } else {
            String message = "Kunne ikke oprette en kontrakt";
            ra.addFlashAttribute("fail", message);

        }

        return "redirect:/lease";
    }



    @GetMapping("delete/contract/{contractID}")
    public String deleteContract(@PathVariable("contractID") int contractID, RedirectAttributes ra){

        boolean isDeleted = contractService.deleteContract(contractID);

        if (isDeleted){
            String message = "kontrakt: " + contractID + " slettet";
            ra.addFlashAttribute("succes", message);
        } else {
            String message = "kontrakt: " + contractID + " kunne ikke slettes, husk at afslutte alle leases før du slutter en kontrakt";
            ra.addFlashAttribute("fail", message);
        }






    return "redirect:/lease";
    }




}
