package com.virusvaccine.bookVaccine.entity;

import com.virusvaccine.user.entity.AgencyEntity;
import com.virusvaccine.vaccine.dto.VaccineRegistrationRequest;
import com.virusvaccine.vaccine.entity.VaccineEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "acquired_vaccine", schema = "virusvaccine", catalog = "")
public class AcquiredVaccineEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;
    @Column(name = "amount")
    private int amount;
    @Column(name = "vaccinate_at")
    private Date vaccinateAt;
    @Column(name = "rest_amount")
    private int restAmount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id", referencedColumnName = "id", nullable = false)
    private AgencyEntity agency;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccine_id", referencedColumnName = "id", nullable = false)
    private VaccineEntity vaccine;

    public AcquiredVaccineEntity(){}

    public AcquiredVaccineEntity(VaccineRegistrationRequest r, AgencyEntity a, VaccineEntity v){
        amount = r.getAmount();
        vaccinateAt = Date.valueOf(r.getVaccinateAt().toLocalDate());
        agency = a;
        vaccine = v;
    }

    public Long getId() {
        return id;
    }

    public int getAmount() {
    return amount;
}

    public Date getVaccinateAt() {
        return vaccinateAt;
    }

    public int getRestAmount() {
    return restAmount;
}

    public void decreaseRestAmount(){restAmount--;}

    public AgencyEntity getAgency() {
    return agency;
}

    public VaccineEntity getVaccine() {
        return vaccine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcquiredVaccineEntity that = (AcquiredVaccineEntity) o;
        return Objects.equals(id, that.id) && amount == that.amount && restAmount == that.restAmount && Objects.equals(vaccinateAt, that.vaccinateAt) && Objects.equals(agency, that.agency) && Objects.equals(vaccine, that.vaccine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, vaccinateAt, restAmount, agency, vaccine);
    }
}
