package com.virusvaccine.dto;


public class ReturnedAgency {

    private final Long id;
    private final String name;
    private final String phoneNumber;
    private final String zipCode;
    private final String siDo;
    private final String siGunGu;
    private final String eupMyeonDong;
    private final String address;
    private final int vaccineId;
    private final int restAmount;

    public ReturnedAgency(Long id, String name, String phoneNumber, String zipCode,
        String siDo, String siGunGu, String eupMyeonDong, String address, int vaccineId,
        int restAmount) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
        this.siDo = siDo;
        this.siGunGu = siGunGu;
        this.eupMyeonDong = eupMyeonDong;
        this.address = address;
        this.vaccineId = vaccineId;
        this.restAmount = restAmount;
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

    public int getVaccineId() {
        return vaccineId;
    }

    public int getRestAmount() {
        return restAmount;
    }

    @Override
    public String toString() {
        return "ReturnedAgency{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", zipCode='" + zipCode + '\'' +
            ", siDo='" + siDo + '\'' +
            ", siGunGu='" + siGunGu + '\'' +
            ", eupMyeonDong='" + eupMyeonDong + '\'' +
            ", address='" + address + '\'' +
            ", vaccineId=" + vaccineId +
            ", restAmount=" + restAmount +
            '}';
    }

    public static final class ReturnedAgencyBuilder {

        private Long id;
        private String name;
        private String phoneNumber;
        private String zipCode;
        private String siDo;
        private String siGunGu;
        private String eupMyeonDong;
        private String address;
        private int vaccineId;
        private int restAmount;

        public static ReturnedAgencyBuilder aReturnedAgency() {
            return new ReturnedAgencyBuilder();
        }

        public ReturnedAgencyBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ReturnedAgencyBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ReturnedAgencyBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public ReturnedAgencyBuilder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public ReturnedAgencyBuilder withSiDo(String siDo) {
            this.siDo = siDo;
            return this;
        }

        public ReturnedAgencyBuilder withSiGunGu(String siGunGu) {
            this.siGunGu = siGunGu;
            return this;
        }

        public ReturnedAgencyBuilder withEupMyeonDong(String eupMyeonDong) {
            this.eupMyeonDong = eupMyeonDong;
            return this;
        }

        public ReturnedAgencyBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public ReturnedAgencyBuilder withVaccineId(int vaccineId) {
            this.vaccineId = vaccineId;
            return this;
        }

        public ReturnedAgencyBuilder withRestAmount(int restAmount) {
            this.restAmount = restAmount;
            return this;
        }

        public ReturnedAgency build() {
            return new ReturnedAgency(id, name, phoneNumber, zipCode, siDo, siGunGu, eupMyeonDong,
                address, vaccineId, restAmount);
        }
    }
}
