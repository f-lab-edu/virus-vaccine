package com.virusvaccine.lookupStats.mapper;

import com.virusvaccine.lookupAgency.dto.ReturnedAgency;
import com.virusvaccine.lookupStats.dto.ReturnedRegion;
import com.virusvaccine.lookupStats.dto.VaccineQuantity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LookupStatsMapper {

  List<VaccineQuantity> getQuantityOfVaccines();

  List<VaccineQuantity> getQuantityOfBookedVaccines();

  List<ReturnedAgency> getAgencysWithRestAmount();

  List<ReturnedRegion> getRegionsWithRestAmount();
}
