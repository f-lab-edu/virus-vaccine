package com.virusvaccine.dto;

import java.io.Serializable;
import java.util.Objects;

public class CalculatedReturnedRegion implements Serializable {

  private final String siDo;
  private Integer totalAmount = 0;

  public CalculatedReturnedRegion(String siDo) {
    this.siDo = siDo;
  }

  public String getSiDo() {
    return siDo;
  }

  public Integer getTotalAmount() {
    return totalAmount;
  }

  public void addTotal(int num){
    totalAmount += num;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CalculatedReturnedRegion)) {
      return false;
    }
    CalculatedReturnedRegion that = (CalculatedReturnedRegion) o;
    return Objects.equals(siDo, that.siDo) && Objects.equals(totalAmount,
        that.totalAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(siDo, totalAmount);
  }

  @Override
  public String toString() {
    return "CalculatedReturnedRegion{" +
        "siDo='" + siDo + '\'' +
        ", totalAmount=" + totalAmount +
        '}';
  }

}
