package com.virusvaccine.mapper;

import com.virusvaccine.dto.Agency;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface AgencyMapper {
    Optional<Agency> getByEmail(String agencyEmail);

    void signUp(Agency agency);
}
