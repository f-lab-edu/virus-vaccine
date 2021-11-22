package com.virusvaccine.lookupStats.mapper;

import com.virusvaccine.lookupAgency.dto.ReturnedAgency;
import com.virusvaccine.lookupStats.dto.ReturnedRegion;
import com.virusvaccine.lookupStats.dto.VaccineQuantity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.virusvaccine.common.annotation.SetDataSource;
import com.virusvaccine.common.annotation.SetDataSource.DataSourceType;

@Mapper
public interface LookupStatsMapper {

  @SetDataSource(dataSourceType = DataSourceType.BOTH)
  List<VaccineQuantity> getQuantityOfVaccines();

  @SetDataSource(dataSourceType = DataSourceType.BOTH)
  List<VaccineQuantity> getQuantityOfBookedVaccines();

  @SetDataSource(dataSourceType = DataSourceType.BOTH)
  List<ReturnedAgency> getAgencysWithRestAmount();

  @SetDataSource(dataSourceType = DataSourceType.BOTH)
  List<ReturnedRegion> getRegionsWithRestAmount();
}
