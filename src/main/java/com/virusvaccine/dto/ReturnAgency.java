package com.virusvaccine.dto;

import java.util.List;
import java.util.Objects;

public class ReturnAgency {

    Long id;
    String phoneNumber;
    String zipCode;
    String siDo;
    String siGunGu;
    String eupMyeonDong;
    String address;
    int vaccineId;
    int restAmount;

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

    public int getVaccineId() {
        return vaccineId;
    }

    public int getRestAmount() {
        return restAmount;
    }

    @Override
    public String toString() {
        return "ReturnAgency{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", siDo='" + siDo + '\'' +
                ", siGunGu='" + siGunGu + '\'' +
                ", eupMyeonDong='" + eupMyeonDong + '\'' +
                ", address='" + address + '\'' +
                ", vaccineId=" + vaccineId +
                ", restAmount=" + restAmount +
                '}';
    }
}
