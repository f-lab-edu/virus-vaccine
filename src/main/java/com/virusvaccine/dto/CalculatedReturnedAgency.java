package com.virusvaccine.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;


public class CalculatedReturnedAgency implements Serializable {

    private final Long id;
    private final String name;
    private final String phoneNumber;
    private final String zipCode;
    private final String siDo;
    private final String siGunGu;
    private final String eupMyeonDong;
    private final String address;
    private final int[] restAmount = new int[5];
    private Integer totalAmount = 0;

    public CalculatedReturnedAgency(Long id, String name, String phoneNumber, String zipCode, String siDo, String siGunGu, String eupMyeonDong, String address) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
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

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void addTotal(int num){
        totalAmount += num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CalculatedReturnedAgency)) {
            return false;
        }
        CalculatedReturnedAgency that = (CalculatedReturnedAgency) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name)
            && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(
            zipCode, that.zipCode) && Objects.equals(siDo, that.siDo)
            && Objects.equals(siGunGu, that.siGunGu) && Objects.equals(eupMyeonDong,
            that.eupMyeonDong) && Objects.equals(address, that.address)
            && Arrays.equals(restAmount, that.restAmount) && Objects.equals(
            totalAmount, that.totalAmount);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, phoneNumber, zipCode, siDo, siGunGu, eupMyeonDong,
            address,
            totalAmount);
        result = 31 * result + Arrays.hashCode(restAmount);
        return result;
    }

    @Override
    public String toString() {
        return "CalculatedReturnedAgency{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", zipCode='" + zipCode + '\'' +
            ", siDo='" + siDo + '\'' +
            ", siGunGu='" + siGunGu + '\'' +
            ", eupMyeonDong='" + eupMyeonDong + '\'' +
            ", address='" + address + '\'' +
            ", restAmount=" + Arrays.toString(restAmount) +
            ", totalAmount=" + totalAmount +
            '}';
    }


    public static final class CalculatedReturnedAgencyBuilder {

        private Long id;
        private String name;
        private String phoneNumber;
        private String zipCode;
        private String siDo;
        private String siGunGu;
        private String eupMyeonDong;
        private String address;

        private CalculatedReturnedAgencyBuilder() {
        }

        public static CalculatedReturnedAgencyBuilder aCalculatedReturnedAgency() {
            return new CalculatedReturnedAgencyBuilder();
        }

        public CalculatedReturnedAgencyBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CalculatedReturnedAgencyBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CalculatedReturnedAgencyBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CalculatedReturnedAgencyBuilder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public CalculatedReturnedAgencyBuilder withSiDo(String siDo) {
            this.siDo = siDo;
            return this;
        }

        public CalculatedReturnedAgencyBuilder withSiGunGu(String siGunGu) {
            this.siGunGu = siGunGu;
            return this;
        }

        public CalculatedReturnedAgencyBuilder withEupMyeonDong(String eupMyeonDong) {
            this.eupMyeonDong = eupMyeonDong;
            return this;
        }

        public CalculatedReturnedAgencyBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public CalculatedReturnedAgency build() {
            return new CalculatedReturnedAgency(id, name, phoneNumber, zipCode, siDo, siGunGu,
                eupMyeonDong, address);
        }
    }
}
