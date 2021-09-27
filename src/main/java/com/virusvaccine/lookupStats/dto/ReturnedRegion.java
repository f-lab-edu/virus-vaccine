package com.virusvaccine.lookupStats.dto;

public class ReturnedRegion {

  private final String siDo;
  private final int restAmount;

  public ReturnedRegion(String siDo, int restAmount) {
    this.siDo = siDo;
    this.restAmount = restAmount;
  }

  public String getSiDo() {
    return siDo;
  }

  public int getRestAmount() {
    return restAmount;
  }

  @Override
  public String toString() {
    return "ReturnedRegion{" +
        "siDo='" + siDo + '\'' +
        ", restAmount=" + restAmount +
        '}';
  }

}
