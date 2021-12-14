package com.virusvaccine.lookupReservation.mapper;

import com.virusvaccine.lookupReservation.dto.AgencyReservationInfo;
import com.virusvaccine.lookupReservation.dto.AgencyReservationInfoWithTime;
import com.virusvaccine.lookupReservation.dto.UserReservationInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LookupReservationMapper {
  //todo: booking
  List<UserReservationInfo> userReservationLookup(Long userId);
  //todo: agency
  List<AgencyReservationInfo> agencyReservationLookup(Long agencyId);
  //todo: booking
  List<AgencyReservationInfoWithTime> agencyReservationLookupWithTime(Long agencyId);

}
