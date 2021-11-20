package com.virusvaccine.lookupStats.mapper;

import com.virusvaccine.lookupStats.dto.ReturnedSortedAgency;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LookupStatsMapper {

  List<Integer> getQuantityOfVaccines();

  List<Integer> getQuantityOfBookedVaccines();

  List<ReturnedSortedAgency> getAgencysWithRestAmount();

  List<String> getRegionsWithRestAmount();

}
