package com.example.rentalcarflowmanagerwebapp.repository;

import com.example.rentalcarflowmanagerwebapp.model.DamageReport;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Arjaco
class DamageReportRepositoryTest {

  @Test
  void shouldReturnListOfContractIDs() {
    var damageRepo = new DamageReportRepository();
    assertNotEquals(0, damageRepo.getAllContractIds().size());
  }

  @Test
  void shouldReturnListOfLeases() {
    var damageRepo = new DamageReportRepository();
    assertNotEquals(0, damageRepo.getAllLeases().size());
  }

  @Test
  void shouldReturnListOfDamageReports() {
    var damageRepo = new DamageReportRepository();
    assertNotEquals(0, damageRepo.getAllDamageReports().size());
  }

  @Test
  void damageReportShouldNotBeNull() {
    var damageRepo = new DamageReportRepository();
    assertNotNull(damageRepo.getDamageReport(6, 4));
  }

  @Test
  void shouldReturnInstanceWithDamageReportIDOfSix() {
    var damageRepo = new DamageReportRepository();
    assertEquals(6, damageRepo.getDamageReport(6, 4).getDamage_report_id());
  }

  @Test
  void shouldCreateDamageReportWithCarIDSix() {
    var damageRepo = new DamageReportRepository();
    damageRepo.createDamageReport(8, 7, "test", 300, "test");
    assertEquals(7, damageRepo.getDamageReport(7, 8).getCar_id());
    int damageID = damageRepo.getDamageReport(7, 8).getDamage_report_id();
    damageRepo.deleteDamageReport(damageID);
  }

  @Test
  void editDamageReport() {
    var damageRepo = new DamageReportRepository();
    damageRepo.editDamageReport(1, "Smadret rude", 200, "CarShop");
    assertEquals(200, damageRepo.getDamageReport(1, 1).getDamage_price_dkk());
    damageRepo.editDamageReport(1, "Bule", 100, "RepairShop");
  }

  @Test
  void deleteDamageReport() {
    var damageRepo = new DamageReportRepository();
    damageRepo.createDamageReport(9, 8, "Test", 100, "test");
    int damageID = damageRepo.getDamageReport(8, 9).getDamage_report_id();
    damageRepo.deleteDamageReport(damageID);
    assertNull(damageRepo.getDamageReport(8, 7));
  }
}