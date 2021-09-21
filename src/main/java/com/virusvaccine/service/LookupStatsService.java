package com.virusvaccine.service;

import com.virusvaccine.config.CacheScheduleConfig;
import com.virusvaccine.dto.CalculatedReturnedAgency;
import com.virusvaccine.dto.CalculatedReturnedRegion;
import com.virusvaccine.dto.RankedReturnedAgency;
import com.virusvaccine.dto.ReturnedAgency;
import com.virusvaccine.dto.ReturnedRegion;
import com.virusvaccine.dto.VaccineQuantity;
import com.virusvaccine.mapper.LookupStatsMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class LookupStatsService {

  @Autowired
  LookupStatsMapper lookupStatsMapper;

  @Cacheable(CacheScheduleConfig.value1)
  public Long[] getQuantityOfVaccines() {

    List<VaccineQuantity> vaccineQuantities = lookupStatsMapper.getQuantityOfVaccines();

    Long[][] quantity = {{1L, 0L}, {2L, 0L}, {3L, 0L}, {4L, 0L}, {5L, 0L}};
    for (VaccineQuantity vaccineQuantity: vaccineQuantities){
      quantity[vaccineQuantity.getVaccineId()-1][1] += vaccineQuantity.getAmount();
    }

    Arrays.sort(quantity, Comparator.comparingLong(o -> -o[1]));

    Long[] ranking = new Long[5];
    for (int i=0; i< quantity.length; i++){
      ranking[i] = quantity[i][0];
    }

    return ranking;
  }

  @Cacheable("QuantityOfBookedVaccines")
  public Long[] getQuantityOfBookedVaccines() {

    List<VaccineQuantity> vaccineQuantities = lookupStatsMapper.getQuantityOfBookedVaccines();

    Long[][] bookedquantity = {{1L, 0L}, {2L, 0L}, {3L, 0L}, {4L, 0L}, {5L, 0L}};

    for (VaccineQuantity vaccineQuantity: vaccineQuantities){
      bookedquantity[vaccineQuantity.getVaccineId()-1][1] += vaccineQuantity.getAmount();
    }

    Arrays.sort(bookedquantity, Comparator.comparingLong(o -> -o[1]));

    Long[] ranking = new Long[5];
    for (int i=0; i< bookedquantity.length; i++){
      ranking[i] = bookedquantity[i][0];
    }

    return ranking;
  }

  @Cacheable("AgencysWithRestAmount")
  public List<RankedReturnedAgency> getAgencysWithRestAmount() {

    List<ReturnedAgency> returnedAgencies = lookupStatsMapper.getAgencysWithRestAmount();

    HashMap<Long, CalculatedReturnedAgency> agencyContainer = new HashMap<>();
    for (ReturnedAgency returnedAgency : returnedAgencies){
      if(!agencyContainer.containsKey(returnedAgency.getId())){
        agencyContainer.put(returnedAgency.getId(), new CalculatedReturnedAgency(returnedAgency.getId(),
            returnedAgency.getName(),
            returnedAgency.getPhoneNumber(),
            returnedAgency.getZipCode(),
            returnedAgency.getSiDo(),
            returnedAgency.getSiGunGu(),
            returnedAgency.getEupMyeonDong(),
            returnedAgency.getAddress()));
      }
      CalculatedReturnedAgency calculatedReturnedAgency = agencyContainer.get(returnedAgency.getId());
      calculatedReturnedAgency.addTotal(returnedAgency.getRestAmount());
    }

    List<CalculatedReturnedAgency> calculatedReturnedAgencies = new ArrayList<>(agencyContainer.values());
    calculatedReturnedAgencies.sort((x1, x2) -> -x1.getTotalAmount().compareTo(x2.getTotalAmount()));
    calculatedReturnedAgencies = calculatedReturnedAgencies.size() > 5 ? calculatedReturnedAgencies.subList(0, 5) : calculatedReturnedAgencies;

    List<RankedReturnedAgency> ranking = new ArrayList<>();
    for (CalculatedReturnedAgency agency : calculatedReturnedAgencies) {
      ranking.add(new RankedReturnedAgency(agency.getName(), agency.getPhoneNumber(), agency.getZipCode(),
              agency.getSiDo(), agency.getSiGunGu(), agency.getEupMyeonDong(), agency.getAddress()));
    }

    return ranking;

  }

  @Cacheable("RegionsWithRestAmount")
  public List<String> getRegionsWithRestAmount() {

    List<ReturnedRegion> returnedRegions = lookupStatsMapper.getRegionsWithRestAmount();

    HashMap<String, CalculatedReturnedRegion> regionContainer = new HashMap<>();
    for (ReturnedRegion returnedRegion : returnedRegions){
      if(!regionContainer.containsKey(returnedRegion.getSiDo())){
        regionContainer.put(returnedRegion.getSiDo(), new CalculatedReturnedRegion(returnedRegion.getSiDo()));
      }
      CalculatedReturnedRegion calculatedReturnedRegion = regionContainer.get(returnedRegion.getSiDo());
      calculatedReturnedRegion.addTotal(returnedRegion.getRestAmount());
    }

    List<CalculatedReturnedRegion> calculatedReturnedRegions = new ArrayList<>(regionContainer.values());
    calculatedReturnedRegions.sort((x1, x2) -> -x1.getTotalAmount().compareTo(x2.getTotalAmount()));
    calculatedReturnedRegions = calculatedReturnedRegions.size() > 5 ? calculatedReturnedRegions.subList(0, 5) : calculatedReturnedRegions;

    List<String> ranking = new ArrayList<>();
    for (CalculatedReturnedRegion region: calculatedReturnedRegions){
      ranking.add(region.getSiDo());
    }

    return ranking;

  }

}
