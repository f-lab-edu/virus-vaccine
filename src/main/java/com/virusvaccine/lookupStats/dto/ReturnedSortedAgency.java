package com.virusvaccine.lookupStats.dto;

import java.io.Serializable;
import java.util.Objects;

public class ReturnedSortedAgency implements Serializable {

  private final String name;
  private final String phoneNumber;
  private final String zipCode;
  private final String siDo;
  private final String siGunGu;
  private final String eupMyeonDong;
  private final String address;

  public ReturnedSortedAgency(String name, String phoneNumber, String zipCode, String siDo,
      String siGunGu, String eupMyeonDong, String address) {
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
    if (!(o instanceof ReturnedSortedAgency)) {
      return false;
    }
    ReturnedSortedAgency that = (ReturnedSortedAgency) o;
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

  public static final class ReturnedSortedAgencyBuilder {

    private String name;
    private String phoneNumber;
    private String zipCode;
    private String siDo;
    private String siGunGu;
    private String eupMyeonDong;
    private String address;

    private ReturnedSortedAgencyBuilder() {
    }

    public static ReturnedSortedAgencyBuilder aReturnedSortedAgency() {
      return new ReturnedSortedAgencyBuilder();
    }

    public ReturnedSortedAgencyBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public ReturnedSortedAgencyBuilder withPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
      return this;
    }

    public ReturnedSortedAgencyBuilder withZipCode(String zipCode) {
      this.zipCode = zipCode;
      return this;
    }

    public ReturnedSortedAgencyBuilder withSiDo(String siDo) {
      this.siDo = siDo;
      return this;
    }

    public ReturnedSortedAgencyBuilder withSiGunGu(String siGunGu) {
      this.siGunGu = siGunGu;
      return this;
    }

    public ReturnedSortedAgencyBuilder withEupMyeonDong(String eupMyeonDong) {
      this.eupMyeonDong = eupMyeonDong;
      return this;
    }

    public ReturnedSortedAgencyBuilder withAddress(String address) {
      this.address = address;
      return this;
    }

    public ReturnedSortedAgency build() {
      return new ReturnedSortedAgency(name, phoneNumber, zipCode, siDo, siGunGu, eupMyeonDong,
          address);
    }
  }
}
