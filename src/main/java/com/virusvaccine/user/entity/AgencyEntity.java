package com.virusvaccine.user.entity;

import com.virusvaccine.user.dto.Agency;
import com.virusvaccine.user.dto.Member;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "agency", schema = "virusvaccine", catalog = "")
public class AgencyEntity implements Member {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "si_do")
    private String siDo;
    @Column(name = "si_gun_gu")
    private String siGunGu;
    @Column(name = "eup_myeon_dong")
    private String eupMyeonDong;
    @Column(name = "address")
    private String address;
    @Column(name = "lat")
    private double lat;
    @Column(name = "lng")
    private double lng;
    @Column(name = "lat_lon")
    private Point latLon;

    public AgencyEntity(){}

    public AgencyEntity(Agency agency){
        email = agency.getEmail();
        password = agency.getPassword();
        name = agency.getName();
        phoneNumber = agency.getPhoneNumber();
        zipCode = agency.getZipCode();
        siDo = agency.getSiDo();
        siGunGu = agency.getSiGunGu();
        eupMyeonDong = agency.getEupMyeonDong();
        address = agency.getAddress();
        lat = agency.getLat();
        lng = agency.getLng();
        latLon = new Point(lat,lng);
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

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public Point getLatLon() {
        return latLon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgencyEntity that = (AgencyEntity) o;
        return Objects.equals(id, that.id) && Double.compare(that.lat, lat) == 0 && Double.compare(that.lng, lng) == 0 && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(name, that.name) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(zipCode, that.zipCode) && Objects.equals(siDo, that.siDo) && Objects.equals(siGunGu, that.siGunGu) && Objects.equals(eupMyeonDong, that.eupMyeonDong) && Objects.equals(address, that.address) && Objects.equals(latLon, that.latLon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, phoneNumber, zipCode, siDo, siGunGu, eupMyeonDong, address, lat, lng, latLon);
    }
}
