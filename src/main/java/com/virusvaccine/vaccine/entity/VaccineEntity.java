package com.virusvaccine.vaccine.entity;

import com.virusvaccine.vaccine.dto.Vaccine;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vaccine", schema = "virusvaccine", catalog = "")
public class VaccineEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "dose")
    private int dose;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "virus_id", referencedColumnName = "id", nullable = false)
    private VirusEntity virus;

    public VaccineEntity() {
    }

    public VaccineEntity(Vaccine vaccine, VirusEntity virus) {
        this.id = vaccine.getId();
        this.code = vaccine.getCode();
        this.name = vaccine.getName();
        this.dose = vaccine.getDose();
        this.virus = virus;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getDose() {
        return dose;
    }

    public VirusEntity getVirus() {
        return virus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VaccineEntity that = (VaccineEntity) o;
        return dose == that.dose && Objects.equals(id, that.id) && Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(virus, that.virus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, dose, virus);
    }
}
