package com.virusvaccine.user.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@JsonTypeName(SignUpRequest.USER_TYPE)
public class UserSignupRequest implements SignUpRequest {

    @Email(message = "이메일 형식을 확인해 주세요")
    @NotBlank(message = "이메일을 입력해 주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해 주세요")
    @Length(min = 4, max = 20, message = "비밀번호는 4~20 글자로 입력해 주세요")
    private String password1;

    @NotBlank(message = "비밀번호를 재입력해 주세요")
    @Length(min = 4, max = 20, message = "비밀번호는 4~20 글자로 입력해 주세요")
    private String password2;

    @NotBlank(message = "이름을 입력해 주세요")
    @Length(max = 10, message = "이름이 10글자를 넘어가지 않게 입력해 주세요")
    private String name;

    @NotBlank(message = "휴대폰번호를 입력해 주세요")
    @Length(min = 11, max = 11, message = "휴대폰번호를 올바르게 입력해 주세요")
    private String phoneNumber;

    @NotBlank(message = "주민등록번호를 입력해 주세요")
    @Length(min = 7, max = 7, message = "주민등록번호 앞 6자리와 뒤 1자리를 올바르게 입력해 주세요")
    private String idNumber;

    public UserSignupRequest() {
    }

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

    @Override
    public boolean validatePassword() {
        return password1.equals(password2);
    }

    @Override
    public boolean isAgency() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSignupRequest that = (UserSignupRequest) o;
        return Objects.equals(email, that.email) && Objects.equals(password1, that.password1) && Objects.equals(password2, that.password2) && Objects.equals(name, that.name) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(idNumber, that.idNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password1, password2, name, phoneNumber, idNumber);
    }
}
