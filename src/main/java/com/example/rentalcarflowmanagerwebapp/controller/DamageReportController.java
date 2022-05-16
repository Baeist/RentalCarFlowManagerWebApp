package com.example.rentalcarflowmanagerwebapp.controller;

import com.example.rentalcarflowmanagerwebapp.service.DamageReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Klasse lavet af Arjaco
@Controller
public class DamageReportController {
  @Autowired
  DamageReportService damageReportService;

  @GetMapping("/{contract_id}/{carId}/damage_report")
  public String getDamageReport (@PathVariable("carId") int carId, @PathVariable("contract_id") int contract_id, Model model) {
    model.addAttribute("damagereport", damageReportService.getDamageReport(carId, contract_id));
    return "damage_report";
  }
  @GetMapping("/{contract_id}/{carId}/create_damage_report_form")
  public String createDamageReportForm() {
    return "damage_report_form";
  }
  @PostMapping("/{contract_id}/{carId}/create_damage_report")
  public void createDamageReport(
      @PathVariable("contract_id") int contract_id,
      @PathVariable("carID") int carId,
      @RequestParam("description") String description,
      @RequestParam("damage_price_dkk") int damage_price_dkk,
      @RequestParam("garage_name") String garage_name) {
    damageReportService.createDamageReport(contract_id, carId, description , damage_price_dkk, garage_name);
  }
  @PostMapping("/{contract_id}/{carId}/delete_damage_report")
  public void deleteDamageReport(@PathVariable("contract_id") int contract_id, @PathVariable("carId") int carId) {
    damageReportService.deleteDamageReport(contract_id, carId);
  }
}
