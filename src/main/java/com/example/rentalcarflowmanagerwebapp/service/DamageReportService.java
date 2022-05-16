package com.example.rentalcarflowmanagerwebapp.service;

import com.example.rentalcarflowmanagerwebapp.model.DamageReportModel;
import com.example.rentalcarflowmanagerwebapp.repository.DamageReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Klasse lavet af Arjaco
@Service
public class DamageReportService {
  @Autowired
  DamageReportRepository damageReportRepository;

  public List<DamageReportModel> getDamageReport (int carId, int contract_id) {
    return damageReportRepository.getDamageReport(carId, contract_id);
  }
  public void createDamageReport(int contract_id, int carId, String description, int damage_price_dkk, String garage_name) {
    damageReportRepository.createDamageReport(contract_id, carId, description, damage_price_dkk, garage_name);
  }
  public void editDamageReport(int damage_report_id, String description, int damage_price_dkk, String garage_name) {
    damageReportRepository.editDamageReport(damage_report_id, description, damage_price_dkk, garage_name);
  }
  public void deleteDamageReport(int damage_report_id) {
    damageReportRepository.deleteDamageReport(damage_report_id);
  }
}
