package com.virusvaccine.mapper;

import com.virusvaccine.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface LookupMapper {
    List<ReturnedAgency> lookup(@Param("lookupRequest") LookupRequest lookupRequest, @Param("code") int code, @Param("nextDay") LocalDate nextDay);

    List<UserReservationInfo> userReservationLookup(Long userId);

    List<AgencyReservationInfo> agencyReservationLookup(Long agencyId);

    List<AgencyReservationInfoWithTime> agencyReservationLookupWithTime(Long agencyId);
}
