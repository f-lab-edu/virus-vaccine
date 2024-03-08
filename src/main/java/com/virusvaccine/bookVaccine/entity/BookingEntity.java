package com.virusvaccine.bookVaccine.entity;

import com.virusvaccine.user.entity.UserEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "booking", schema = "virusvaccine", catalog = "")
@IdClass(BookingEntityPK.class)
public class BookingEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @Id
    @ManyToOne
    @JoinColumn(name = "acquired_vaccine_id", referencedColumnName = "id", nullable = false)
    private AcquiredVaccineEntity acquiredVaccine;

    @Column(name = "vaccinate_at")
    private Timestamp vaccinateAt;

    public BookingEntity(){}

    public BookingEntity(UserEntity user, AcquiredVaccineEntity acquiredVaccine, LocalDateTime vaccinateAt){
        this.user = user;
        this.acquiredVaccine = acquiredVaccine;
        this.vaccinateAt = Timestamp.valueOf(vaccinateAt);
    }

    public Timestamp getVaccinateAt() {
        return vaccinateAt;
    }

    public UserEntity getUser() {
        return user;
    }

    public AcquiredVaccineEntity getAcquiredVaccine() {
        return acquiredVaccine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingEntity that = (BookingEntity) o;
        return Objects.equals(user, that.user) && Objects.equals(acquiredVaccine, that.acquiredVaccine) && Objects.equals(vaccinateAt, that.vaccinateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, acquiredVaccine, vaccinateAt);
    }
}
