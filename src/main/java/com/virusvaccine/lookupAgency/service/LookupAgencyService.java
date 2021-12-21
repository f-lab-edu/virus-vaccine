package com.virusvaccine.lookupAgency.service;

import com.virusvaccine.bookVaccine.entity.AcquiredVaccineEntity;
import com.virusvaccine.bookVaccine.repository.AcquiredVaccineRepository;
import com.virusvaccine.common.exception.NotFoundException;
import com.virusvaccine.lookupAgency.dto.CalculatedReturnedAgency;
import com.virusvaccine.lookupAgency.dto.LookupRequest;
import com.virusvaccine.user.entity.AgencyEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LookupAgencyService {
    private final AcquiredVaccineRepository acquiredVaccineRepository;

    public LookupAgencyService(AcquiredVaccineRepository acquiredVaccineRepository) {
        this.acquiredVaccineRepository = acquiredVaccineRepository;
    }

    public List<CalculatedReturnedAgency> lookup(LookupRequest lookupRequest) {

        List<AcquiredVaccineEntity> returnedAcquiredVaccines = acquiredVaccineRepository.lookup(lookupRequest);

        if (returnedAcquiredVaccines.isEmpty())
            throw new NotFoundException();

        HashMap<Long, CalculatedReturnedAgency> agencyContainer = new HashMap<>();
        for (AcquiredVaccineEntity v : returnedAcquiredVaccines) {
            AgencyEntity a = v.getAgency();
            if (!agencyContainer.containsKey(a.getId())) {
                agencyContainer.put(a.getId(), new CalculatedReturnedAgency(a.getId(),
                        a.getName(),
                        a.getPhoneNumber(),
                        a.getZipCode(),
                        a.getSiDo(),
                        a.getSiGunGu(),
                        a.getEupMyeonDong(),
                        a.getAddress()));
            }
            CalculatedReturnedAgency calculatedReturnedAgency = agencyContainer.get(a.getId());
            calculatedReturnedAgency.getRestAmount()[(int) (v.getVaccine().getId() - 1)] += v.getRestAmount();
            calculatedReturnedAgency.addTotal(v.getRestAmount());
        }
        return new ArrayList<>(agencyContainer.values());
    }
}
