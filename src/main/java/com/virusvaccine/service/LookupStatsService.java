package com.virusvaccine.service;

import com.virusvaccine.dto.CalculatedReturnedAgency;
import com.virusvaccine.dto.CalculatedReturnedRegion;
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
import org.springframework.stereotype.Service;

@Service
public class LookupStatsService {

  @Autowired
  LookupStatsMapper lookupStatsMapper;

  public Long[][] getQuantityOfVaccines() {

    List<VaccineQuantity> vaccineQuantities = lookupStatsMapper.getQuantityOfVaccines();
    Long[][] quantity = {{1L, 0L}, {2L, 0L}, {3L, 0L}, {4L, 0L}, {5L, 0L}};

    for (VaccineQuantity vaccineQuantity: vaccineQuantities){
      quantity[vaccineQuantity.getVaccineId()-1][1] += vaccineQuantity.getAmount();
    }

    Arrays.sort(quantity, Comparator.comparingLong(o -> -o[1]));

    return quantity;
  }

  public Long[][] getQuantityOfBookedVaccines() {

    List<VaccineQuantity> vaccineQuantities = lookupStatsMapper.getQuantityOfBookedVaccines();
    Long[][] bookedquantity = {{1L, 0L}, {2L, 0L}, {3L, 0L}, {4L, 0L}, {5L, 0L}};

    for (VaccineQuantity vaccineQuantity: vaccineQuantities){
      bookedquantity[vaccineQuantity.getVaccineId()-1][1] += vaccineQuantity.getAmount();
    }

    Arrays.sort(bookedquantity, Comparator.comparingLong(o -> -o[1]));

    return bookedquantity;
  }

  public List<CalculatedReturnedAgency> getAgencysWithRestAmount() {

    List<ReturnedAgency> returnedAgencies = lookupStatsMapper.getAgencysWithRestAmount();

    HashMap<Long, CalculatedReturnedAgency> agencyContainer = new HashMap<>();
    for (ReturnedAgency returnedAgency : returnedAgencies){
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

    List<CalculatedReturnedAgency> calculatedReturnedAgencies = new ArrayList<>(agencyContainer.values());
    calculatedReturnedAgencies.sort((x1, x2) -> -x1.getTotalAmount().compareTo(x2.getTotalAmount()));

    return calculatedReturnedAgencies.size() > 5 ? calculatedReturnedAgencies.subList(0, 5) : calculatedReturnedAgencies;
  }

  public List<CalculatedReturnedRegion> getRegionsWithRestAmount() {

    List<ReturnedRegion> returnedRegions = lookupStatsMapper.getRegionsWithRestAmount();

    HashMap<String, CalculatedReturnedRegion> regionContainer = new HashMap<>();
    for (ReturnedRegion returnedRegion : returnedRegions){
      if(!regionContainer.containsKey(returnedRegion.getSiDo())){
        regionContainer.put(returnedRegion.getSiDo(), new CalculatedReturnedRegion(returnedRegion.getSiDo()));
      }
      CalculatedReturnedRegion calculatedReturnedRegion = regionContainer.get(returnedRegion.getSiDo());
      calculatedReturnedRegion.getRestAmount()[returnedRegion.getVaccineId()-1] += returnedRegion.getRestAmount();
      calculatedReturnedRegion.addTotal(returnedRegion.getRestAmount());
    }

    List<CalculatedReturnedRegion> calculatedReturnedRegions = new ArrayList<>(regionContainer.values());
    calculatedReturnedRegions.sort((x1, x2) -> -x1.getTotalAmount().compareTo(x2.getTotalAmount()));

    return calculatedReturnedRegions.size() > 5 ? calculatedReturnedRegions.subList(0, 5) : calculatedReturnedRegions;

  }

}
