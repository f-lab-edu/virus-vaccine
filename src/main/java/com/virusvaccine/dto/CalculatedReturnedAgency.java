package com.virusvaccine.dto;


import java.util.Arrays;
import java.util.Objects;

public class CalculatedReturnedAgency {

    private final Long id;
    private final String phoneNumber;
    private final String zipCode;
    private final String siDo;
    private final String siGunGu;
    private final String eupMyeonDong;
    private final String address;
    private final int[] restAmount = new int[5];

    public CalculatedReturnedAgency(Long id, String phoneNumber, String zipCode, String siDo, String siGunGu, String eupMyeonDong, String address) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
        this.siDo = siDo;
        this.siGunGu = siGunGu;
        this.eupMyeonDong = eupMyeonDong;
        this.address = address;
    }

    public Long getId() {
        return id;
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

    public int[] getRestAmount() {
        return restAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CalculatedReturnedAgency)) return false;
        CalculatedReturnedAgency that = (CalculatedReturnedAgency) o;
        return Objects.equals(id, that.id) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(zipCode, that.zipCode) && Objects.equals(siDo, that.siDo) && Objects.equals(siGunGu, that.siGunGu) && Objects.equals(eupMyeonDong, that.eupMyeonDong) && Objects.equals(address, that.address) && Arrays.equals(restAmount, that.restAmount);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, phoneNumber, zipCode, siDo, siGunGu, eupMyeonDong, address);
        result = 31 * result + Arrays.hashCode(restAmount);
        return result;
    }

    @Override
    public String toString() {
        return "CalculatedReturnedAgency{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", siDo='" + siDo + '\'' +
                ", siGunGu='" + siGunGu + '\'' +
                ", eupMyeonDong='" + eupMyeonDong + '\'' +
                ", address='" + address + '\'' +
                ", restAmount=" + Arrays.toString(restAmount) +
                '}';
    }
}
