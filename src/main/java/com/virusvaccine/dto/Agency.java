package com.virusvaccine.dto;

public class Agency {
    private final Long id;    // id
    private final String email;    // 이메일 (uniqe)
    private final String password;    // 비밀번호
    private final String name;    // 기관 명
    private final String phoneNumber;    // 전화번호
    private final String zipCode;    // 우편번호
    private final String siDo;    // 시/도
    private final String siGunGu;    // 시/군/구
    private final String eupMyeonDong;    // 읍/면/동/도로명
    private final String address;    // 나머지 주소
    private final Float lat;    // 위도
    private final Float lng;    // 경도

    public Agency(Long id, String email, String password, String name, String phoneNumber, String zipCode, String siDo, String siGunGu, String eupMyeonDong, String address, Float lat, Float lng) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
        this.siDo = siDo;
        this.siGunGu = siGunGu;
        this.eupMyeonDong = eupMyeonDong;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }

    private Agency(Builder builder) {
        id = builder.id;
        email = builder.email;
        password = builder.password;
        name = builder.name;
        phoneNumber = builder.phoneNumber;
        zipCode = builder.zipCode;
        siDo = builder.siDo;
        siGunGu = builder.siGunGu;
        eupMyeonDong = builder.eupMyeonDong;
        address = builder.address;
        lat = builder.lat;
        lng = builder.lng;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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

    public Float getLat() {
        return lat;
    }

    public Float getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", siDo='" + siDo + '\'' +
                ", siGunGu='" + siGunGu + '\'' +
                ", eupMyeonDong='" + eupMyeonDong + '\'' +
                ", address='" + address + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    public static class Builder {
        private Long id;
        private String email;
        private String password;
        private String name;
        private String phoneNumber;
        private String zipCode;
        private String siDo;
        private String siGunGu;
        private String eupMyeonDong;
        private String address;
        private Float lat;
        private Float lng;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder siDo(String siDo) {
            this.siDo = siDo;
            return this;
        }

        public Builder siGunGu(String siGunGu) {
            this.siGunGu = siGunGu;
            return this;
        }

        public Builder eupMyeonDong(String eupMyeonDong) {
            this.eupMyeonDong = eupMyeonDong;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder lat(Float lat) {
            this.lat = lat;
            return this;
        }

        public Builder lng(Float lng) {
            this.lng = lng;
            return this;
        }

        public Agency build(){
            return new Agency(this);
        }
    }
}
