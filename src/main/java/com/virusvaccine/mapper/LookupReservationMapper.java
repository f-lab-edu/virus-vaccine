package com.virusvaccine.mapper;

import com.virusvaccine.dto.AgencyReservationInfo;
import com.virusvaccine.dto.AgencyReservationInfoWithTime;
import com.virusvaccine.dto.UserReservationInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LookupReservationMapper {

  List<UserReservationInfo> userReservationLookup(Long userId);

  List<AgencyReservationInfo> agencyReservationLookup(Long agencyId);

  List<AgencyReservationInfoWithTime> agencyReservationLookupWithTime(Long agencyId);

}
