package com.virusvaccine.user.mapper;

import com.virusvaccine.common.annotation.SetDataSource;
import com.virusvaccine.common.annotation.SetDataSource.DataSourceType;
import com.virusvaccine.user.dto.Agency;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface AgencyMapper {
    @SetDataSource(dataSourceType = DataSourceType.BOTH)
    Optional<Agency> getByEmail(String agencyEmail);

    @SetDataSource(dataSourceType = DataSourceType.MASTER)
    void signUp(Agency agency);
}
