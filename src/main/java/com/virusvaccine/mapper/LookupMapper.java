package com.virusvaccine.mapper;

import com.virusvaccine.dto.LookupRequest;
import com.virusvaccine.dto.ReturnedAgency;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.LinkedList;

@Mapper
public interface LookupMapper {
    LinkedList<ReturnedAgency> lookup(@Param("lookupRequest") LookupRequest lookupRequest, @Param("code") int code, @Param("nextDay") LocalDate nextDay);
}
