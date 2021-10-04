package com.virusvaccine.bookVaccine.mapper;

import com.virusvaccine.bookVaccine.dto.ReservationRequest;
import com.virusvaccine.bookVaccine.dto.SearchResult;
import com.virusvaccine.lookupAgency.dto.VaccineType;
import com.virusvaccine.lookupStats.dto.VaccineQuantity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReservationMapper {

  Optional<SearchResult> search(@Param("agencyId") Long agencyId, @Param("vaccineId") int vaccineId);

  void decreaseRestAmount(Long id);

  void bookVaccine(@Param("userId") Long userId, @Param("id") Long id, @Param("vaccinateAt") LocalDateTime vaccinateAt);

  void updateState(@Param("userId") Long userId, @Param("vaccineId") int vaccineId);

}
