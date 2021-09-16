package com.virusvaccine.dto;

public class ReturnedRegion {

  private final String siDo;
  private final int vaccineId;
  private final int restAmount;

  public ReturnedRegion(String siDo, int vaccineId, int restAmount) {
    this.siDo = siDo;
    this.vaccineId = vaccineId;
    this.restAmount = restAmount;
  }

  public String getSiDo() {
    return siDo;
  }

  public int getVaccineId() {
    return vaccineId;
  }

  public int getRestAmount() {
    return restAmount;
  }
}
