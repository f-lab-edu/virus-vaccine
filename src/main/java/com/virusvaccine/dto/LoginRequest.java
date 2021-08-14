package com.virusvaccine.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @Email(message = "이메일 형식을 확인 해주세요")
    @NotBlank(message = "이메일을 입력 해주세요")
    private String userEmail;

    @NotBlank(message = "비밀번호를 입력 해주세요")
    private String userPassword;

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
}
