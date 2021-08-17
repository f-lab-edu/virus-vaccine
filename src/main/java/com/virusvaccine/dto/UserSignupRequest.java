package com.virusvaccine.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserSignupRequest implements SignUpRequest {

    @Email(message = "이메일 형식을 확인해 주세요")
    @NotBlank(message = "이메일을 입력해 주세요")
    private final String email;

    @NotBlank(message = "비밀번호를 입력해 주세요")
    @Length(min = 4, max = 20, message = "비밀번호는 4~20 글자로 입력해 주세요")
    private final String password1;

    @NotBlank(message = "비밀번호를 재입력해 주세요")
    @Length(min = 4, max = 20, message = "비밀번호는 4~20 글자로 입력해 주세요")
    private final String password2;

    @NotBlank(message = "이름을 입력해 주세요")
    @Length(max = 10, message = "이름이 10글자를 넘어가지 않게 입력해 주세요")
    private final String name;

    @NotBlank(message = "휴대폰번호를 입력해 주세요")
    @Length(min = 11, max = 11, message = "휴대폰번호를 올바르게 입력해 주세요")
    private final String phoneNumber;

    @NotBlank(message = "주민등록번호를 입력해 주세요")
    @Length(min = 7, max = 7, message = "주민등록번호 앞 6자리와 뒤 1자리를 올바르게 입력해 주세요")
    private final String idNumber;

    public UserSignupRequest(String email, String password1, String password2, String name, String phoneNumber, String idNumber) {
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword1() {
        return password1;
    }

    public String getPassword2() {
        return password2;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    @Override
    public String toString() {
        return "SignupRequest{" +
                "email='" + email + '\'' +
                ", password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", idNumber='" + idNumber + '\'' +
                '}';
    }
}
