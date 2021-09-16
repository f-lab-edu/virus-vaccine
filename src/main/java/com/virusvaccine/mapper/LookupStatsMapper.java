package com.virusvaccine.mapper;

import com.virusvaccine.dto.ReturnedAgency;
import com.virusvaccine.dto.ReturnedRegion;
import com.virusvaccine.dto.VaccineQuantity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LookupStatsMapper {

  List<VaccineQuantity> getQuantityOfVaccines();

  List<VaccineQuantity> getQuantityOfBookedVaccines();

  List<ReturnedAgency> getAgencysWithRestAmount();

  List<ReturnedRegion> getRegionsWithRestAmount();
}
