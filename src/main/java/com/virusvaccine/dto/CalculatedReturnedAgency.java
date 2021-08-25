package com.virusvaccine.dto;


import java.util.Arrays;

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
