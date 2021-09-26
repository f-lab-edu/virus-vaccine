package com.virusvaccine.service;

import com.virusvaccine.dto.AgencyReservationInfo;
import com.virusvaccine.dto.AgencyReservationInfoWithTime;
import com.virusvaccine.dto.CalculatedAgencyReservationInfo;
import com.virusvaccine.dto.UserReservationInfo;
import com.virusvaccine.exception.NotFoundException;
import com.virusvaccine.mapper.LookupReservationMapper;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LookupReservationService {

  private final LookupReservationMapper lookupReservationMapper;

  public LookupReservationService(LookupReservationMapper lookupReservationMapper) {
    this.lookupReservationMapper = lookupReservationMapper;
  }

  public List<UserReservationInfo> lookupReservation(Long userId) {

    List<UserReservationInfo> userReservationInfos = lookupReservationMapper.userReservationLookup(userId);

    if (userReservationInfos.isEmpty()){
      throw new NotFoundException();
    }

    return userReservationInfos;
  }

  public HashMap<LocalDate, CalculatedAgencyReservationInfo> lookupAgencyReservation(Long agencyId) {

    List<AgencyReservationInfo> agencyReservationInfos = lookupReservationMapper.agencyReservationLookup(agencyId);

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

    List<AgencyReservationInfoWithTime> agencyReservationInfoWithTimes = lookupReservationMapper.agencyReservationLookupWithTime(agencyId);

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
