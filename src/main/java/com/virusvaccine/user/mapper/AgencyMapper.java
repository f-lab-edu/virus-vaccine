package com.virusvaccine.user.mapper;

import com.virusvaccine.user.dto.Agency;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface AgencyMapper {
    Optional<Agency> getByEmail(String agencyEmail);

    void signUp(Agency agency);
}
