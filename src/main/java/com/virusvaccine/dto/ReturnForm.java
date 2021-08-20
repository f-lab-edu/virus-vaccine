package com.virusvaccine.dto;


public class ReturnForm {

    Long id;
    String phoneNumber;
    String zipCode;
    String siDo;
    String siGunGu;
    String eupMyeonDong;
    String address;
    int[] restAmount = new int[5];

    public ReturnForm(Long id, String phoneNumber, String zipCode, String siDo, String siGunGu, String eupMyeonDong, String address) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
        this.siDo = siDo;
        this.siGunGu = siGunGu;
        this.eupMyeonDong = eupMyeonDong;
        this.address = address;
    }

    public Long getId() {
        return id;
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

    public int[] getRestAmount() {
        return restAmount;
    }
}
