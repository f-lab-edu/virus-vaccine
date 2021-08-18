package com.virusvaccine.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@JsonTypeName(SignUpRequest.AGENCY_TYPE)
public class AgencySignUpRequest implements SignUpRequest {
    @Email(message = "이메일 형식을 확인해 주세요")
    @NotBlank(message = "이메일을 입력해 주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해 주세요")
    @Length(min = 4, max = 20, message = "비밀번호는 4~20 글자로 입력해 주세요")
    private String password;

    @NotBlank(message = "비밀번호를 입력해 주세요")
    @Length(min = 4, max = 20, message = "비밀번호는 4~20 글자로 입력해 주세요")
    private String validPassword;

    @NotBlank(message = "이름을 입력해 주세요")
    @Length(max = 20, message = "이름이 20글자를 넘어가지 않게 입력해 주세요")
    private String name;

    @NotBlank(message = "휴대폰번호를 입력해 주세요")
    @Length(min = 11, max = 11, message = "휴대폰번호를 올바르게 입력해 주세요")
    private String phoneNumber;

    @NotBlank(message = "우편번호를 입력해 주세요")
    private String zipCode;

    @NotBlank(message = "시/도를 입력해 주세요")
    private String siDo;

    @NotBlank(message = "시/군/구를 입력해 주세요")
    private String siGunGu;

    @NotBlank(message = "읍/면/동/도로명을 입력해 주세요")
    private String eupMyeonDong;

    @NotBlank(message = "나머지 주소를 입력해 주세요")
    private String address;

    @NotNull
    private Double lat;

    @NotNull
    private Double lng;

    public AgencySignUpRequest() {
    }

    public AgencySignUpRequest(String email, String password, String validPassword, String name, String phoneNumber, String zipCode, String siDo, String siGunGu, String eupMyeonDong, String address, Double lat, Double lng) {
        this.email = email;
        this.password = password;
        this.validPassword = validPassword;
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

    private AgencySignUpRequest(Builder builder) {
        email = builder.email;
        password = builder.password;
        validPassword = builder.validPassword;
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getValidPassword() {
        return validPassword;
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

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return "AgencySignUpRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", validPassword='" + validPassword + '\'' +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgencySignUpRequest that = (AgencySignUpRequest) o;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(validPassword, that.validPassword) && Objects.equals(name, that.name) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(zipCode, that.zipCode) && Objects.equals(siDo, that.siDo) && Objects.equals(siGunGu, that.siGunGu) && Objects.equals(eupMyeonDong, that.eupMyeonDong) && Objects.equals(address, that.address) && Objects.equals(lat, that.lat) && Objects.equals(lng, that.lng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, validPassword, name, phoneNumber, zipCode, siDo, siGunGu, eupMyeonDong, address, lat, lng);
    }

    @Override
    public boolean validatePassword() {
        return password.equals(validPassword);
    }

    @Override
    public boolean isAgency() {
        return true;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String email;
        private String password;
        private String validPassword;
        private String name;
        private String phoneNumber;
        private String zipCode;
        private String siDo;
        private String siGunGu;
        private String eupMyeonDong;
        private String address;
        private Double lat;
        private Double lng;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder validPassword(String validPassword) {
            this.validPassword = validPassword;
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

        public Builder lat(Double lat) {
            this.lat = lat;
            return this;
        }

        public Builder lng(Double lng) {
            this.lng = lng;
            return this;
        }

        public AgencySignUpRequest build() {
            return new AgencySignUpRequest(this);
        }
    }
}
