package com.example.rentalcarflowmanagerwebapp.controller;

import com.example.rentalcarflowmanagerwebapp.model.Lease;
import com.example.rentalcarflowmanagerwebapp.repository.ContractRepository;
import com.example.rentalcarflowmanagerwebapp.service.CarService;
import com.example.rentalcarflowmanagerwebapp.service.ContractService;
import com.example.rentalcarflowmanagerwebapp.service.DamageReportService;
import com.example.rentalcarflowmanagerwebapp.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

// Klasse lavet af Arjaco
@Controller
public class DamageReportController {

    DamageReportService damageReportService;
    ContractService contractService;
    LeaseService leaseService;
    CarService carService;


    public DamageReportController(DamageReportService damageReportService, ContractService contractService,
                                  LeaseService leaseService, CarService carService) {

        this.damageReportService = damageReportService;
        this.contractService = contractService;
        this.leaseService = leaseService;
    }


    @GetMapping("/damage_report_dashboard")
    public String getDamageReportDashboard(Model model) {

        model.addAttribute("location", "damageRapport");

        ArrayList<Lease> allLeases = leaseService.getAllLeases();

        model.addAttribute("allContracts", contractService.GetAllContractsWithCars());
        model.addAttribute("listOfLeases", damageReportService.doesLeaseHaveDamageReport(allLeases));
        model.addAttribute("listOfDamageReports", damageReportService.getAllDamageReports());


        return "damage_report_dashboard";
    }

    @GetMapping("/damage_report/{contract_id}/{carId}")
    public String getDamageReport(@PathVariable("carId") int carId, @PathVariable("contract_id") int contract_id, Model model) {
        model.addAttribute("damageReport", damageReportService.getDamageReport(carId, contract_id));
        return "damage_report_view";
    }



    @GetMapping("/{contract_id}/{carId}/create_damage_report_form")
    public String createDamageReportForm(
            @PathVariable("contract_id") int contract_id,
            @PathVariable("carId") int carId,
            Model model) {

        model.addAttribute("contract_id", contract_id);
        model.addAttribute("carId", carId);
        return "damage_report_create_form";
    }


    @PostMapping("/create/damage_report/{contract_id}/{carId}")
    public String createDamageReport(
            @PathVariable("contract_id") int contract_id,
            @PathVariable("carId") int carId,
            @RequestParam("description") String description,
            @RequestParam("damage_price_dkk") int damage_price_dkk,
            @RequestParam("garage_name") String garage_name) {
        damageReportService.createDamageReport(contract_id, carId, description, damage_price_dkk, garage_name);
        return "redirect:/damage_report_dashboard";
    }





    @GetMapping("/edit_damage_report/{contract_id}/{car_id}")
    public String editDamageReportForm(Model model, @PathVariable("contract_id") int contract_id, @PathVariable("car_id") int car_id) {
        model.addAttribute("damageReport", damageReportService.getDamageReport(car_id, contract_id));
        return "damage_report_edit_form";
    }

    @PostMapping("/edit_damage_report")
    public String editDamageReport(
            @RequestParam("damage_report_id") int damage_report_id,
            @RequestParam("description") String description,
            @RequestParam("damage_price_dkk") int damage_price_dkk,
            @RequestParam("garage_name") String garage_name) {
        damageReportService.editDamageReport(damage_report_id, description, damage_price_dkk, garage_name);
        return "redirect:/damage_report_dashboard";
    }

    @GetMapping("/{damage_report_id}/delete_damage_report")
    public String deleteDamageReport(@PathVariable("damage_report_id") int damage_report_id) {
        damageReportService.deleteDamageReport(damage_report_id);
        return "redirect:/damage_report_dashboard";
    }
}
