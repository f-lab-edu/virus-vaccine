package com.virusvaccine.bookVaccine.entity;

import com.virusvaccine.user.entity.UserEntity;
import com.virusvaccine.vaccine.entity.VaccineEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vaccinated_state", schema = "virusvaccine", catalog = "")
@IdClass(VaccinatedStateEntityPK.class)
public class VaccinatedStateEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;
    @Id
    @ManyToOne
    @JoinColumn(name = "vaccine_id", referencedColumnName = "id", nullable = false)
    private VaccineEntity vaccine;
    @Column(name = "dose_num")
    private int doseNum;

    public VaccinatedStateEntity() {
    }

    public VaccinatedStateEntity(UserEntity user, VaccineEntity vaccine, int doseNum) {
        this.user = user;
        this.vaccine = vaccine;
        this.doseNum = doseNum;
    }

    public UserEntity getUserByUserId() {
        return user;
    }

    public VaccineEntity getVaccine() {
        return vaccine;
    }

    public int getDoseNum() {
        return doseNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VaccinatedStateEntity that = (VaccinatedStateEntity) o;
        return doseNum == that.doseNum && Objects.equals(user, that.user) && Objects.equals(vaccine, that.vaccine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, vaccine, doseNum);
    }
}
