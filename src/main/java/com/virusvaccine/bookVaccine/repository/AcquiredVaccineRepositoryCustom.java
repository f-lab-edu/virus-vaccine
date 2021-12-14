package com.virusvaccine.bookVaccine.repository;

import com.virusvaccine.bookVaccine.entity.AcquiredVaccineEntity;
import com.virusvaccine.lookupAgency.dto.LookupRequest;

import java.sql.Date;
import java.util.List;

public interface AcquiredVaccineRepositoryCustom {

    List<AcquiredVaccineEntity> searchAvailable(Long agency_id, Long vaccine_id, Date today);
    List<AcquiredVaccineEntity> lookup(LookupRequest req);

    List<Long> getQuantityOfVaccines();
    List<Long> getQuantityOfBookedVaccines();

    }
