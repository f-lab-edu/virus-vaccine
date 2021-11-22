package com.virusvaccine.bookVaccine.mapper;

import com.virusvaccine.bookVaccine.dto.SearchResult;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.virusvaccine.common.annotation.SetDataSource;
import com.virusvaccine.common.annotation.SetDataSource.DataSourceType;

@Mapper
public interface ReservationMapper {

  @SetDataSource(dataSourceType = DataSourceType.MASTER)
  Optional<SearchResult> searchAvailable(@Param("agencyId") Long agencyId, @Param("vaccineId") int vaccineId, @Param("today") LocalDate today);

  @SetDataSource(dataSourceType = DataSourceType.MASTER)
  int decreaseRestAmount(@Param("id") Long id, @Param("restAmount") Long restAmount);

  @SetDataSource(dataSourceType = DataSourceType.MASTER)
  void bookVaccine(@Param("userId") Long userId, @Param("id") Long id, @Param("vaccinateAt") LocalDateTime vaccinateAt);

  @SetDataSource(dataSourceType = DataSourceType.MASTER)
  void updateState(@Param("userId") Long userId, @Param("vaccineId") int vaccineId);

}
