package com.virusvaccine.dto;

import java.io.Serializable;
import java.util.Arrays;

public class CalculatedReturnedRegion implements Serializable {

  private final String siDo;
  private Integer totalAmount = 0;
  private final int[] restAmount = new int[5];

  public CalculatedReturnedRegion(String siDo) {
    this.siDo = siDo;
  }

  public String getSiDo() {
    return siDo;
  }

  public Integer getTotalAmount() {
    return totalAmount;
  }

  public int[] getRestAmount() {
    return restAmount;
  }

  public void addTotal(int num){
    totalAmount += num;
  }

  @Override
  public String toString() {
    return "CalculatedReturnedRegion{" +
        "siDo='" + siDo + '\'' +
        ", totalAmount=" + totalAmount +
        ", restAmount=" + Arrays.toString(restAmount) +
        '}';
  }

}
