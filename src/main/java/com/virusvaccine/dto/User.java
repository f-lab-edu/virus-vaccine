package com.virusvaccine.dto;

public class User {

    private final int id;
    private final String email;
    private final String password;
    private final String name;
    private final String phoneNumber;
    private final String idNumber;

    public User(int id, String email, String password, String name, String phoneNumber, String idNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.idNumber = idNumber;
    }

    public int getId() {
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
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", idNumber='" + idNumber + '\'' +
                '}';
    }
}
