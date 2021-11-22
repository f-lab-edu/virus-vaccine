package com.virusvaccine.lookupReservation.mapper;

import com.virusvaccine.lookupReservation.dto.AgencyReservationInfo;
import com.virusvaccine.lookupReservation.dto.AgencyReservationInfoWithTime;
import com.virusvaccine.lookupReservation.dto.UserReservationInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.virusvaccine.common.annotation.SetDataSource;
import com.virusvaccine.common.annotation.SetDataSource.DataSourceType;

@Mapper
public interface LookupReservationMapper {

  @SetDataSource(dataSourceType = DataSourceType.BOTH)
  List<UserReservationInfo> userReservationLookup(Long userId);

  @SetDataSource(dataSourceType = DataSourceType.BOTH)
  List<AgencyReservationInfo> agencyReservationLookup(Long agencyId);

  @SetDataSource(dataSourceType = DataSourceType.BOTH)
  List<AgencyReservationInfoWithTime> agencyReservationLookupWithTime(Long agencyId);

}
