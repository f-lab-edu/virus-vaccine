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


    public static final class AcquiredVaccineEntityBuilder {
        private Long id;
        private int amount;
        private Date vaccinateAt;
        private int restAmount;
        private AgencyEntity agency;
        private VaccineEntity vaccine;

        private AcquiredVaccineEntityBuilder() {
        }

        public static AcquiredVaccineEntityBuilder anAcquiredVaccineEntity() {
            return new AcquiredVaccineEntityBuilder();
        }

        public AcquiredVaccineEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AcquiredVaccineEntityBuilder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public AcquiredVaccineEntityBuilder vaccinateAt(Date vaccinateAt) {
            this.vaccinateAt = vaccinateAt;
            return this;
        }

        public AcquiredVaccineEntityBuilder restAmount(int restAmount) {
            this.restAmount = restAmount;
            return this;
        }

        public AcquiredVaccineEntityBuilder agency(AgencyEntity agency) {
            this.agency = agency;
            return this;
        }

        public AcquiredVaccineEntityBuilder vaccine(VaccineEntity vaccine) {
            this.vaccine = vaccine;
            return this;
        }

        public AcquiredVaccineEntity build() {
            AcquiredVaccineEntity acquiredVaccineEntity = new AcquiredVaccineEntity();
            acquiredVaccineEntity.id = this.id;
            acquiredVaccineEntity.amount = this.amount;
            acquiredVaccineEntity.vaccinateAt = this.vaccinateAt;
            acquiredVaccineEntity.agency = this.agency;
            acquiredVaccineEntity.vaccine = this.vaccine;
            acquiredVaccineEntity.restAmount = this.restAmount;
            return acquiredVaccineEntity;
        }
    }
}
