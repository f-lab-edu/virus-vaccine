package com.virusvaccine.service;

import com.virusvaccine.dto.LookupRequest;
import com.virusvaccine.dto.ReturnAgency;
import com.virusvaccine.dto.ReturnForm;
import com.virusvaccine.mapper.LookupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LookupService {

    @Autowired
    private LookupMapper lookupMapper;

    public List<ReturnForm> lookup(LookupRequest lookupRequest) {

        List<ReturnForm> toReturn;
        List<ReturnAgency> returnedAgencys = new ArrayList<>();
        // 백신종류x, 보유유무x, 날짜x
        if(lookupRequest.getCode() == null && !lookupRequest.isAvailable() && lookupRequest.getDate() == null){
            returnedAgencys = lookupMapper.lookup(lookupRequest);
        }
        // 백신종류O, 보유유무x, 날짜x
        else if(lookupRequest.getCode() != null && !lookupRequest.isAvailable() && lookupRequest.getDate() == null){
            returnedAgencys = lookupMapper.lookupWithCode(lookupRequest, lookupRequest.getCode().getType());
        }
        // 백신종류x, 보유유무O, 날짜x
        else if(lookupRequest.getCode() == null && lookupRequest.isAvailable() && lookupRequest.getDate() == null){
            returnedAgencys = lookupMapper.lookupWithAvailable(lookupRequest);
        }
        // 백신종류O, 보유유무O, 날짜x
        else if(lookupRequest.getCode() != null && lookupRequest.isAvailable() && lookupRequest.getDate() == null){
            returnedAgencys = lookupMapper.lookupWithCodeWithAvailable(lookupRequest, lookupRequest.getCode().getType());
        }
        // 백신종류x, 보유유무x, 날짜o(미래 날짜)
        else if(lookupRequest.getCode() == null && !lookupRequest.isAvailable()){
            returnedAgencys = lookupMapper.lookupWithDate(lookupRequest, lookupRequest.getDate().plusDays(1));
        }
        // 백신종류O, 보유유무x, 날짜o(미래 날짜)
        else if(lookupRequest.getCode() != null && !lookupRequest.isAvailable()){
            returnedAgencys = lookupMapper.lookupWithCodeWithDate(lookupRequest, lookupRequest.getCode().getType(), lookupRequest.getDate().plusDays(1));
        }
        // 백신종류x, 보유유무O, 날짜o(미래 날짜)
        else if(lookupRequest.getCode() == null && lookupRequest.isAvailable()){
            returnedAgencys = lookupMapper.lookupWithAvailableWithDate(lookupRequest, lookupRequest.getDate().plusDays(1));
        }
        // 백신종류O, 보유유무O, 날짜o(미래 날짜)
        else if(lookupRequest.getCode() != null && lookupRequest.isAvailable() ){
            returnedAgencys = lookupMapper.lookupWithCodeWithAvailableWithDate(lookupRequest, lookupRequest.getCode().getType(), lookupRequest.getDate().plusDays(1));
        }

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
