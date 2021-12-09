package com.virusvaccine.bookVaccine.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class BookingEntityPK implements Serializable {
    private Long user;
    private Long acquiredVaccine;

    @Column(name = "user_id")
    @Id
    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    @Column(name = "acquired_vaccine_id")
    @Id
    public Long getAcquiredVaccine() {
        return acquiredVaccine;
    }

    public void setAcquiredVaccine(Long acquiredVaccine) {
        this.acquiredVaccine = acquiredVaccine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingEntityPK that = (BookingEntityPK) o;
        return Objects.equals(user, that.user) && Objects.equals(acquiredVaccine, that.acquiredVaccine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, acquiredVaccine);
    }
}
