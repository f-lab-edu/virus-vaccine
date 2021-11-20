package com.virusvaccine.lookupStats.service;

import com.virusvaccine.common.config.CacheScheduleConfig;
import com.virusvaccine.lookupStats.dto.ReturnedSortedAgency;
import com.virusvaccine.lookupStats.mapper.LookupStatsMapper;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class LookupStatsService {

  private final LookupStatsMapper lookupStatsMapper;

  public LookupStatsService(LookupStatsMapper lookupStatsMapper) {
    this.lookupStatsMapper = lookupStatsMapper;
  }

  @Cacheable(CacheScheduleConfig.VALUE_RANKING_QUANTITY)
  public List<Integer> getQuantityOfVaccines() {

    return lookupStatsMapper.getQuantityOfVaccines();

  }

  @Cacheable(CacheScheduleConfig.VALUE_RANKING_BOOKEDNUM)
  public List<Integer> getQuantityOfBookedVaccines() {

    return lookupStatsMapper.getQuantityOfBookedVaccines();

  }

  @Cacheable(CacheScheduleConfig.VALUE_RANKING_AGENCY)
  public List<ReturnedSortedAgency> getAgencysWithRestAmount() {

    return lookupStatsMapper.getAgencysWithRestAmount();

  }

  @Cacheable(CacheScheduleConfig.VALUE_RANKING_REGION)
  public List<String> getRegionsWithRestAmount() {

    return lookupStatsMapper.getRegionsWithRestAmount();

  }

}
