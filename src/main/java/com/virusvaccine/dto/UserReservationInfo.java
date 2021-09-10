package com.virusvaccine.dto;

import java.time.LocalDateTime;

public class UserReservationInfo {

    private final LocalDateTime vaccinateAt;
    private final Long vaccineId;
    private final String name;
    private final String phoneNumber;
    private final String zipCode;
    private final String siDo;
    private final String siGunGu;
    private final String eupMyeonDong;
    private final String address;

    public UserReservationInfo(LocalDateTime vaccinateAt, Long vaccineId, String name, String phoneNumber, String zipCode, String siDo, String siGunGu, String eupMyeonDong, String address) {
        this.vaccinateAt = vaccinateAt;
        this.vaccineId = vaccineId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
        this.siDo = siDo;
        this.siGunGu = siGunGu;
        this.eupMyeonDong = eupMyeonDong;
        this.address = address;
    }

    public LocalDateTime getVaccinateAt() {
        return vaccinateAt;
    }

    public Long getVaccineId() {
        return vaccineId;
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
    public String toString() {
        return "UserReservationInfo{" +
                "vaccinateAt=" + vaccinateAt +
                ", vaccineId=" + vaccineId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", siDo='" + siDo + '\'' +
                ", siGunGu='" + siGunGu + '\'' +
                ", eupMyeonDong='" + eupMyeonDong + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
