package com.virusvaccine.service;

import com.virusvaccine.dto.LookupRequest;
import com.virusvaccine.dto.ReturnedAgency;
import com.virusvaccine.dto.CalculatedReturnedAgency;
import com.virusvaccine.mapper.LookupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class AgencyService {

    @Autowired
    private LookupMapper lookupMapper;

    public List<CalculatedReturnedAgency> lookup(LookupRequest lookupRequest) {

        List<CalculatedReturnedAgency> toReturn;
        List<ReturnedAgency> returnedAgencys;

        int code = lookupRequest.getCode() == null ? -1: lookupRequest.getCode().getType();
        LocalDate nextDay = lookupRequest.getDate() == null ? null: lookupRequest.getDate().plusDays(1);

        returnedAgencys = lookupMapper.lookup(lookupRequest, code, nextDay);
        returnedAgencys.forEach(x -> System.out.println(x.toString()));

        HashMap<Long, CalculatedReturnedAgency> agencyContainer = new HashMap<>();
        for (ReturnedAgency returnedAgency : returnedAgencys){
            if(!agencyContainer.containsKey(returnedAgency.getId())){
                agencyContainer.put(returnedAgency.getId(), new CalculatedReturnedAgency(returnedAgency.getId(),
                        returnedAgency.getPhoneNumber(),
                        returnedAgency.getZipCode(),
                        returnedAgency.getSiDo(),
                        returnedAgency.getSiGunGu(),
                        returnedAgency.getEupMyeonDong(),
                        returnedAgency.getAddress()));
            }

            agencyContainer.get(returnedAgency.getId()).getRestAmount()[returnedAgency.getVaccineId()-1] += returnedAgency.getRestAmount();

        }

        toReturn = new ArrayList<>(agencyContainer.values());

        return toReturn;

    }
}
