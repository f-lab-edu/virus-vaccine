package com.virusvaccine.vaccine.mapper;

import com.virusvaccine.vaccine.dto.Vaccine;
import com.virusvaccine.vaccine.dto.VaccineRegistrationRequest;
import com.virusvaccine.vaccine.dto.Virus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import com.virusvaccine.common.annotation.SetDataSource;
import com.virusvaccine.common.annotation.SetDataSource.DataSourceType;

@Mapper
public interface VaccineMapper {
    @SetDataSource(dataSourceType = DataSourceType.BOTH)
    List<Virus> getViruses();
    @SetDataSource(dataSourceType = DataSourceType.BOTH)
    List<Vaccine> getVaccines();
    @SetDataSource(dataSourceType = DataSourceType.MASTER)
    void insertAcquiredVaccine(VaccineRegistrationRequest vaccine);
}
