package com.virusvaccine.service;

import com.virusvaccine.dto.Vaccine;
import com.virusvaccine.dto.VaccineRegistrationRequest;
import com.virusvaccine.dto.Virus;
import com.virusvaccine.mapper.VaccineMapper;
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
