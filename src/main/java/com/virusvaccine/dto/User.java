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

    public static class Builder{

        private int id;
        private String email;
        private String password;
        private String name;
        private String phoneNumber;
        private String idNumber;

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder phoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder idNumber(String idNumber){
            this.idNumber = idNumber;
            return this;
        }

        public User build(){
            return new User(id, email, password, name, phoneNumber, idNumber);
        }

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
