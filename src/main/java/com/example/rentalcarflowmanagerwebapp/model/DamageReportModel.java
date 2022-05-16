package com.example.rentalcarflowmanagerwebapp.model;
// Klasse lavet af Arjaco
public class DamageReportModel {
  public DamageReportModel(int damage_report_id, String description, int damage_price_dkk, String garage_name, int contract_id, int car_id) {
    this.damage_report_id = damage_report_id;
    this.description = description;
    this.damage_price_dkk = damage_price_dkk;
    this.garage_name = garage_name;
    this.contract_id = contract_id;
    this.car_id = car_id;
  }
  private int damage_report_id;
  private int contract_id;
  private int car_id;
  private String description;
  private int damage_price_dkk;
  private String garage_name;

  public int getContract_id() {
    return contract_id;
  }

  public int getCar_id() {
    return car_id;
  }

  public int getDamage_report_id() {
    return damage_report_id;
  }

  public String getDescription() {
    return description;
  }

  public int getDamage_price_dkk() {
    return damage_price_dkk;
  }

  public String getGarage_name() {
    return garage_name;
  }

  @Override
  public String toString() {
    return "DamageReportModel{" +
        "damage_report_id=" + damage_report_id +
        ", description='" + description + '\'' +
        ", damage_price_dkk=" + damage_price_dkk +
        ", garage_name='" + garage_name + '\'' +
        '}';
  }
}
