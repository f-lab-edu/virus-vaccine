package com.virusvaccine.service;

import com.virusvaccine.dto.*;
import com.virusvaccine.exception.NotFoundException;
import com.virusvaccine.mapper.LookupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class LookupService {

    @Autowired
    private LookupMapper lookupMapper;

    public List<CalculatedReturnedAgency> lookup(LookupRequest lookupRequest) {

        List<ReturnedAgency> returnedAgencys;

        int code = lookupRequest.getCode() == null ? -1: lookupRequest.getCode().getType();
        LocalDate nextDay = lookupRequest.getDate() == null ? null: lookupRequest.getDate().plusDays(1);

        returnedAgencys = lookupMapper.lookup(lookupRequest, code, nextDay);

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

            agencyContainer.get(returnedAgency.getId()).getRestAmount()[returnedAgency.getVaccineId()-1] += returnedAgency.getRestAmount();

        }

        return new ArrayList<>(agencyContainer.values());

    }

    public List<UserReservationInfo> lookupReservation(Long userId) {

        List<UserReservationInfo> userReservationInfos = lookupMapper.userReservationLookup(userId);

        if (userReservationInfos.isEmpty()){
            throw new NotFoundException();
        }

        return userReservationInfos;
    }

    public HashMap<LocalDate, CalculatedAgencyReservationInfo> lookupAgencyReservation(Long agencyId) {

        List<AgencyReservationInfo> agencyReservationInfos = lookupMapper.agencyReservationLookup(agencyId);

        if (agencyReservationInfos.isEmpty()){
            throw new NotFoundException();
        }

        HashMap<LocalDate, CalculatedAgencyReservationInfo> toReturn = new HashMap<>();

        for (AgencyReservationInfo agencyReservationInfo: agencyReservationInfos){
            LocalDate date = agencyReservationInfo.getVaccinateAt();
            if (!toReturn.containsKey(date)){
                toReturn.put(date, new CalculatedAgencyReservationInfo());
            }
            toReturn.get(date).getAmount()[agencyReservationInfo.getVaccineId()-1] += agencyReservationInfo.getRestAmount();
            toReturn.get(date).getBookedAmount()[agencyReservationInfo.getVaccineId()-1] += agencyReservationInfo.getBookedAmount();
        }

        return toReturn;

    }

    public HashMap<LocalDate, HashMap<Integer, long[]>> lookupAgencyReservationWithTime(Long agencyId){

        List<AgencyReservationInfoWithTime> agencyReservationInfoWithTimes = lookupMapper.agencyReservationLookupWithTime(agencyId);

        if (agencyReservationInfoWithTimes.isEmpty()){
            throw new NotFoundException();
        }

        HashMap<LocalDate, HashMap<Integer, long[]>> toReturn = new HashMap<>();

        for (AgencyReservationInfoWithTime agencyReservationInfoWithTime: agencyReservationInfoWithTimes){
            LocalDate date = agencyReservationInfoWithTime.getVaccinateAt().toLocalDate();
            if (!toReturn.containsKey(date)){
                HashMap<Integer, long[]> vaccinePerHour = new HashMap<>();
                for (int hour=0; hour<24; hour++) { // 0시부터 23시까지
                    vaccinePerHour.put(hour, new long[5]);
                }
                toReturn.put(date, vaccinePerHour);
            }
            toReturn.get(date).get(agencyReservationInfoWithTime.getVaccinateAt().toLocalTime().getHour())[agencyReservationInfoWithTime.getVaccineId()-1] ++;
        }

        return toReturn;

    }
}
