package com.virusvaccine.user.entity;

import com.virusvaccine.user.dto.Member;
import com.virusvaccine.user.dto.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "virusvaccine", catalog = "")
public class UserEntity implements Member {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "id_number")
    private String idNumber;

    public UserEntity(){}

    public UserEntity(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.idNumber = user.getIdNumber();
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

    public String getIdNumber() {
        return idNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(name, that.name) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(idNumber, that.idNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, phoneNumber, idNumber);
    }
}
