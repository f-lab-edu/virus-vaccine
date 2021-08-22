package com.virusvaccine.mapper;

import com.virusvaccine.dto.Vaccine;
import com.virusvaccine.dto.VaccineRegistrationRequest;
import com.virusvaccine.dto.Virus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VaccineMapper {
    List<Virus> getViruses();
    List<Vaccine> getVaccines();
    void insertAcquiredVaccine(VaccineRegistrationRequest vaccine);
}
