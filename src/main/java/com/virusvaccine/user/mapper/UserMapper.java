package com.virusvaccine.user.mapper;

import com.virusvaccine.user.dto.User;
import org.apache.ibatis.annotations.Mapper;
import com.virusvaccine.common.annotation.SetDataSource;
import com.virusvaccine.common.annotation.SetDataSource.DataSourceType;

import java.util.Optional;

@Mapper
public interface UserMapper {
    @SetDataSource(dataSourceType = DataSourceType.BOTH)
    Optional<User> getByEmail(String userEmail);

    @SetDataSource(dataSourceType = DataSourceType.MASTER)
    void signup(User user);
}
