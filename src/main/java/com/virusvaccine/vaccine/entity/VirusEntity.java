package com.virusvaccine.vaccine.entity;

import com.virusvaccine.vaccine.dto.Virus;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "virus", schema = "virusvaccine", catalog = "")
public class VirusEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;

    public VirusEntity() {
    }

    public VirusEntity(Virus v){
        this.code = v.getCode();
        this.name = v.getName();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VirusEntity that = (VirusEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(code, that.code) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name);
    }
}
