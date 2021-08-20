package com.virusvaccine.service;

import com.virusvaccine.dto.LookupRequest;
import com.virusvaccine.dto.ReturnAgency;
import com.virusvaccine.dto.ReturnForm;
import com.virusvaccine.mapper.LookupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class LookupService {

    @Autowired
    private LookupMapper lookupMapper;

    public List<ReturnForm> lookup(LookupRequest lookupRequest) {

        List<ReturnForm> toReturn;
        List<ReturnAgency> returnedAgencys;

        int code = lookupRequest.getCode() == null ? -1: lookupRequest.getCode().getType();
        LocalDate nextDay = lookupRequest.getDate() == null ? null: lookupRequest.getDate().plusDays(1);

        returnedAgencys = lookupMapper.lookup(lookupRequest, code, nextDay);

        HashMap<Long, ReturnForm> agencyContainer = new HashMap<>();
        for (ReturnAgency returnAgency: returnedAgencys){
            if(!agencyContainer.containsKey(returnAgency.getId())){
                agencyContainer.put(returnAgency.getId(), new ReturnForm(returnAgency.getId(),
                        returnAgency.getPhoneNumber(),
                        returnAgency.getZipCode(),
                        returnAgency.getSiDo(),
                        returnAgency.getSiGunGu(),
                        returnAgency.getEupMyeonDong(),
                        returnAgency.getAddress()));

            }
            ReturnForm returnForm = agencyContainer.get(returnAgency.getId());
            returnForm.getRestAmount()[returnAgency.getVaccineId()-1] += returnAgency.getRestAmount();
        }

        toReturn = new ArrayList<>(agencyContainer.values());

        return toReturn;




    }
}
