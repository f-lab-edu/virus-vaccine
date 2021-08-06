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
    private String userLatitude;
    private String userLongtitude;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhonenumber() {
        return userPhonenumber;
    }

    public void setUserPhonenumber(String userPhonenumber) {
        this.userPhonenumber = userPhonenumber;
    }

    public String getUserIdnumber() {
        return userIdnumber;
    }

    public void setUserIdnumber(String userIdnumber) {
        this.userIdnumber = userIdnumber;
    }

    public String getUserZipcode() {
        return userZipcode;
    }

    public void setUserZipcode(String userZipcode) {
        this.userZipcode = userZipcode;
    }

    public String getUserSi() {
        return userSi;
    }

    public void setUserSi(String userSi) {
        this.userSi = userSi;
    }

    public String getUserGu() {
        return userGu;
    }

    public void setUserGu(String userGu) {
        this.userGu = userGu;
    }

    public String getUserDong() {
        return userDong;
    }

    public void setUserDong(String userDong) {
        this.userDong = userDong;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(String userLatitude) {
        this.userLatitude = userLatitude;
    }

    public String getUserLongtitude() {
        return userLongtitude;
    }

    public void setUserLongtitude(String userLongtitude) {
        this.userLongtitude = userLongtitude;
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
                ", userLatitude='" + userLatitude + '\'' +
                ", userLongtitude='" + userLongtitude + '\'' +
                '}';
    }
}
