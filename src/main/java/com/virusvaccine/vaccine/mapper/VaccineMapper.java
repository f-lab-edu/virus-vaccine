package com.virusvaccine.vaccine.mapper;

import com.virusvaccine.vaccine.dto.Vaccine;
import com.virusvaccine.vaccine.dto.VaccineRegistrationRequest;
import com.virusvaccine.vaccine.dto.Virus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VaccineMapper {
    List<Virus> getViruses();
    List<Vaccine> getVaccines();
    void insertAcquiredVaccine(VaccineRegistrationRequest vaccine);
}
