package com.virusvaccine.lookupAgency.mapper;

import com.virusvaccine.lookupAgency.dto.LookupRequest;
import com.virusvaccine.lookupAgency.dto.ReturnedAgency;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;
import com.virusvaccine.common.annotation.SetDataSource;
import com.virusvaccine.common.annotation.SetDataSource.DataSourceType;

@Mapper
public interface LookupAgencyMapper {

    @SetDataSource(dataSourceType = DataSourceType.BOTH)
    List<ReturnedAgency> lookup(@Param("lookupRequest") LookupRequest lookupRequest, @Param("code") int code, @Param("nextDay") LocalDate nextDay);

}
