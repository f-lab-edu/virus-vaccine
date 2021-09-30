package com.virusvaccine.user.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class LoginRequest {

    @Email(message = "이메일 형식을 확인 해주세요")
    @NotBlank(message = "이메일을 입력 해주세요")
    private String userEmail;

    @NotBlank(message = "비밀번호를 입력 해주세요")
    private String userPassword;

    @JsonProperty("isAgency")
    private Boolean isAgency;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Boolean isAgency() {
        return isAgency;
    }

    public void setAgency(Boolean agency) {
        isAgency = agency;
    }

    public LoginRequest() {
    }

    public LoginRequest(String userEmail, String userPassword, Boolean isAgency) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.isAgency = isAgency;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", isAgency='" + isAgency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginRequest that = (LoginRequest) o;
        return Objects.equals(userEmail, that.userEmail) && Objects.equals(userPassword, that.userPassword) && Objects.equals(isAgency, that.isAgency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEmail, userPassword, isAgency);
    }
}
