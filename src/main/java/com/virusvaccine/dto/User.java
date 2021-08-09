package com.virusvaccine.dto;

public class User {

    private int userId;
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userPhonenumber;
    private String userIdnumber;
    private String userZipcode;
    private String userSi;
    private String userGu;
    private String userDong;
    private String userAddress;
    private double userLatitude;
    private double userLongtitude;

    public User(int userId, String userEmail, String userPassword, String userName, String userPhonenumber, String userIdnumber, String userZipcode, String userSi, String userGu, String userDong, String userAddress, double userLatitude, double userLongtitude) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPhonenumber = userPhonenumber;
        this.userIdnumber = userIdnumber;
        this.userZipcode = userZipcode;
        this.userSi = userSi;
        this.userGu = userGu;
        this.userDong = userDong;
        this.userAddress = userAddress;
        this.userLatitude = userLatitude;
        this.userLongtitude = userLongtitude;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhonenumber() {
        return userPhonenumber;
    }

    public String getUserIdnumber() {
        return userIdnumber;
    }

    public String getUserZipcode() {
        return userZipcode;
    }

    public String getUserSi() {
        return userSi;
    }

    public String getUserGu() {
        return userGu;
    }

    public String getUserDong() {
        return userDong;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public double getUserLatitude() {
        return userLatitude;
    }

    public double getUserLongtitude() {
        return userLongtitude;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhonenumber='" + userPhonenumber + '\'' +
                ", userIdnumber='" + userIdnumber + '\'' +
                ", userZipcode='" + userZipcode + '\'' +
                ", userSi='" + userSi + '\'' +
                ", userGu='" + userGu + '\'' +
                ", userDong='" + userDong + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userLatitude=" + userLatitude +
                ", userLongtitude=" + userLongtitude +
                '}';
    }
}
