package com.virusvaccine.lookupStats.dto;

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

  public static final class RankedReturnedAgencyBuilder {

    private String name;
    private String phoneNumber;
    private String zipCode;
    private String siDo;
    private String siGunGu;
    private String eupMyeonDong;
    private String address;

    public static RankedReturnedAgencyBuilder aRankedReturnedAgency() {
      return new RankedReturnedAgencyBuilder();
    }

    public RankedReturnedAgencyBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public RankedReturnedAgencyBuilder withPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
      return this;
    }

    public RankedReturnedAgencyBuilder withZipCode(String zipCode) {
      this.zipCode = zipCode;
      return this;
    }

    public RankedReturnedAgencyBuilder withSiDo(String siDo) {
      this.siDo = siDo;
      return this;
    }

    public RankedReturnedAgencyBuilder withSiGunGu(String siGunGu) {
      this.siGunGu = siGunGu;
      return this;
    }

    public RankedReturnedAgencyBuilder withEupMyeonDong(String eupMyeonDong) {
      this.eupMyeonDong = eupMyeonDong;
      return this;
    }

    public RankedReturnedAgencyBuilder withAddress(String address) {
      this.address = address;
      return this;
    }

    public RankedReturnedAgency build() {
      return new RankedReturnedAgency(name, phoneNumber, zipCode, siDo, siGunGu, eupMyeonDong,
          address);
    }
  }
}
