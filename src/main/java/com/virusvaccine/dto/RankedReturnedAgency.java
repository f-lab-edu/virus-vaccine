package com.virusvaccine.dto;

import java.io.Serializable;
import java.util.Objects;

public class RankedReturnedAgency implements Serializable {

  private final String name;
  private final String phoneNumber;
  private final String zipCode;
  private final String siDo;
  private final String siGunGu;
  private final String eupMyeonDong;
  private final String address;

  public RankedReturnedAgency(String name, String phoneNumber, String zipCode,
      String siDo, String siGunGu, String eupMyeonDong, String address) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.zipCode = zipCode;
    this.siDo = siDo;
    this.siGunGu = siGunGu;
    this.eupMyeonDong = eupMyeonDong;
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getZipCode() {
    return zipCode;
  }

  public String getSiDo() {
    return siDo;
  }

  public String getSiGunGu() {
    return siGunGu;
  }

  public String getEupMyeonDong() {
    return eupMyeonDong;
  }

  public String getAddress() {
    return address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RankedReturnedAgency)) {
      return false;
    }
    RankedReturnedAgency that = (RankedReturnedAgency) o;
    return Objects.equals(name, that.name) && Objects.equals(phoneNumber,
        that.phoneNumber) && Objects.equals(zipCode, that.zipCode)
        && Objects.equals(siDo, that.siDo) && Objects.equals(siGunGu,
        that.siGunGu) && Objects.equals(eupMyeonDong, that.eupMyeonDong)
        && Objects.equals(address, that.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, phoneNumber, zipCode, siDo, siGunGu, eupMyeonDong, address);
  }

  @Override
  public String toString() {
    return "RankedReturnedAgency{" +
        "name='" + name + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", zipCode='" + zipCode + '\'' +
        ", siDo='" + siDo + '\'' +
        ", siGunGu='" + siGunGu + '\'' +
        ", eupMyeonDong='" + eupMyeonDong + '\'' +
        ", address='" + address + '\'' +
        '}';
  }

}
