package com.virusvaccine.service;

import com.virusvaccine.dto.*;
import com.virusvaccine.exception.NotFoundException;
import com.virusvaccine.mapper.LookupAgencyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class LookupAgencyService {

    @Autowired
    private LookupAgencyMapper lookupAgencyMapper;

    public List<CalculatedReturnedAgency> lookup(LookupRequest lookupRequest) {

        List<ReturnedAgency> returnedAgencys;

        int code = lookupRequest.getCode() == null ? -1: lookupRequest.getCode().getType();
        LocalDate nextDay = lookupRequest.getDate() == null ? null: lookupRequest.getDate().plusDays(1);

        returnedAgencys = lookupAgencyMapper.lookup(lookupRequest, code, nextDay);

        if (returnedAgencys.isEmpty()){
            throw new NotFoundException();
        }

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
            CalculatedReturnedAgency calculatedReturnedAgency = agencyContainer.get(returnedAgency.getId());
            calculatedReturnedAgency.getRestAmount()[returnedAgency.getVaccineId()-1] += returnedAgency.getRestAmount();
            calculatedReturnedAgency.addTotal(returnedAgency.getRestAmount());
        }

        return new ArrayList<>(agencyContainer.values());

    }

}
