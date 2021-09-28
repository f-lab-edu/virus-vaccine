package com.virusvaccine.lookupReservation.dto;

import java.time.LocalDateTime;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserReservationInfo)) {
            return false;
        }
        UserReservationInfo that = (UserReservationInfo) o;
        return Objects.equals(vaccinateAt, that.vaccinateAt) && Objects.equals(
            vaccineId, that.vaccineId) && Objects.equals(name, that.name)
            && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(
            zipCode, that.zipCode) && Objects.equals(siDo, that.siDo)
            && Objects.equals(siGunGu, that.siGunGu) && Objects.equals(eupMyeonDong,
            that.eupMyeonDong) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vaccinateAt, vaccineId, name, phoneNumber, zipCode, siDo, siGunGu,
            eupMyeonDong, address);
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


    public static final class UserReservationInfoBuilder {

        private LocalDateTime vaccinateAt;
        private Long vaccineId;
        private String name;
        private String phoneNumber;
        private String zipCode;
        private String siDo;
        private String siGunGu;
        private String eupMyeonDong;
        private String address;

        private UserReservationInfoBuilder() {
        }

        public static UserReservationInfoBuilder anUserReservationInfo() {
            return new UserReservationInfoBuilder();
        }

        public UserReservationInfoBuilder withVaccinateAt(LocalDateTime vaccinateAt) {
            this.vaccinateAt = vaccinateAt;
            return this;
        }

        public UserReservationInfoBuilder withVaccineId(Long vaccineId) {
            this.vaccineId = vaccineId;
            return this;
        }

        public UserReservationInfoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserReservationInfoBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserReservationInfoBuilder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public UserReservationInfoBuilder withSiDo(String siDo) {
            this.siDo = siDo;
            return this;
        }

        public UserReservationInfoBuilder withSiGunGu(String siGunGu) {
            this.siGunGu = siGunGu;
            return this;
        }

        public UserReservationInfoBuilder withEupMyeonDong(String eupMyeonDong) {
            this.eupMyeonDong = eupMyeonDong;
            return this;
        }

        public UserReservationInfoBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public UserReservationInfo build() {
            return new UserReservationInfo(vaccinateAt, vaccineId, name, phoneNumber, zipCode, siDo,
                siGunGu, eupMyeonDong, address);
        }
    }
}
