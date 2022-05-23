package com.example.rentalcarflowmanagerwebapp.repository;

import com.example.rentalcarflowmanagerwebapp.model.DamageReport;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DamageReportRepositoryTest {

  @Test
  void getAllContractIds() {
  }

  @Test
  void getAllLeases() {
  }

  @Test
  void getAllDamageReports() {
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
  void createDamageReport() {
  }

  @Test
  void editDamageReport() {
  }

  @Test
  void deleteDamageReport() {
  }
}