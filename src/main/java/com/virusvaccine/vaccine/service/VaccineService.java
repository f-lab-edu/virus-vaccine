package com.virusvaccine.vaccine.service;

import com.virusvaccine.vaccine.dto.Vaccine;
import com.virusvaccine.vaccine.dto.VaccineRegistrationRequest;
import com.virusvaccine.vaccine.dto.Virus;
import com.virusvaccine.vaccine.mapper.VaccineMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VaccineService {

    private final VaccineMapper vaccineMapper;

    public VaccineService(VaccineMapper vaccineMapper) {
        this.vaccineMapper = vaccineMapper;
    }

    public List<Virus> getViruses() {
        return vaccineMapper.getViruses();
    }

    public List<Vaccine> getVaccines() {
        return vaccineMapper.getVaccines();
    }

    @Transactional
    public void register(Long agencyId, VaccineRegistrationRequest vaccine) {
        vaccine.setAgencyId(agencyId);
        vaccineMapper.insertAcquiredVaccine(vaccine);
    }
}
