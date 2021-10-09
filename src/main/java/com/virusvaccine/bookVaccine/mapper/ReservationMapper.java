package com.virusvaccine.bookVaccine.mapper;

import com.virusvaccine.bookVaccine.dto.SearchResult;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReservationMapper {

  Optional<SearchResult> searchAvailable(@Param("agencyId") Long agencyId, @Param("vaccineId") int vaccineId, @Param("today") LocalDate today);

  int decreaseRestAmount(@Param("id") Long id, @Param("restAmount") Long restAmount);

  void bookVaccine(@Param("userId") Long userId, @Param("id") Long id, @Param("vaccinateAt") LocalDateTime vaccinateAt);

  void updateState(@Param("userId") Long userId, @Param("vaccineId") int vaccineId);

}
