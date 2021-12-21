package com.virusvaccine.bookVaccine.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class VaccinatedStateEntityPK implements Serializable {
    private Long user;
    private Long vaccine;

    @Column(name = "user_id")
    @Id
    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    @Column(name = "vaccine_id")
    @Id
    public Long getVaccine() {
        return vaccine;
    }

    public void setVaccine(Long vaccine) {
        this.vaccine = vaccine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VaccinatedStateEntityPK that = (VaccinatedStateEntityPK) o;
        return Objects.equals(user, that.user) && Objects.equals(vaccine, that.vaccine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, vaccine);
    }
}
