package com.virusvaccine.dto;

public class VaccineQuantity {

  private final int vaccineId;
  private final Long amount;

  public VaccineQuantity(int vaccineId, Long amount) {
    this.vaccineId = vaccineId;
    this.amount = amount;
  }

  public int getVaccineId() {
    return vaccineId;
  }

  public Long getAmount() {
    return amount;
  }

  @Override
  public String toString() {
    return "VaccineQuantity{" +
        "vaccineId=" + vaccineId +
        ", amount=" + amount +
        '}';
  }

}
