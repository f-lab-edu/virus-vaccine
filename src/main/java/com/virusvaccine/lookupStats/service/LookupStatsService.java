package com.virusvaccine.lookupStats.service;

import com.virusvaccine.bookVaccine.repository.AcquiredVaccineRepository;
import com.virusvaccine.common.config.CacheScheduleConfig;
import com.virusvaccine.lookupStats.dto.ReturnedSortedAgency;
import com.virusvaccine.lookupStats.mapper.LookupStatsMapper;
import java.util.List;
import java.util.stream.Collectors;

import com.virusvaccine.user.repository.AgencyRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class LookupStatsService {
  private final AcquiredVaccineRepository acquiredVaccineRepository;
  private final AgencyRepository agencyRepository;

  public LookupStatsService(AcquiredVaccineRepository acquiredVaccineRepository, AgencyRepository agencyRepository) {
    this.acquiredVaccineRepository = acquiredVaccineRepository;
    this.agencyRepository = agencyRepository;
  }

  @Cacheable(CacheScheduleConfig.VALUE_RANKING_QUANTITY)
  public List<Long> getQuantityOfVaccines() {
    return acquiredVaccineRepository.getQuantityOfVaccines();
  }

  @Cacheable(CacheScheduleConfig.VALUE_RANKING_BOOKEDNUM)
  public List<Long> getQuantityOfBookedVaccines() {
    return acquiredVaccineRepository.getQuantityOfBookedVaccines();
  }

  @Cacheable(CacheScheduleConfig.VALUE_RANKING_AGENCY)
  public List<ReturnedSortedAgency> getAgenciesWithRestAmount() {
    return agencyRepository.getAgenciesWithRestAmount().stream()
            .map(a -> new ReturnedSortedAgency(a.getName(), a.getPhoneNumber(), a.getZipCode(), a.getSiDo(), a.getSiGunGu(), a.getEupMyeonDong(),a.getAddress()))
            .collect(Collectors.toList());
  }

  @Cacheable(CacheScheduleConfig.VALUE_RANKING_REGION)
  public List<String> getRegionsWithRestAmount() {
    return agencyRepository.getRegionsWithRestAmount();
  }
}
