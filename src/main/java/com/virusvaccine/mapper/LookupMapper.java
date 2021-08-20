package com.virusvaccine.mapper;

import com.virusvaccine.dto.LookupRequest;
import com.virusvaccine.dto.ReturnAgency;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Mapper
public interface LookupMapper {
    LinkedList<ReturnAgency> lookup(LookupRequest lookupRequest);

    List<ReturnAgency> lookupWithCode(@Param("lookupRequest") LookupRequest lookupRequest, @Param("code") int code);

    List<ReturnAgency> lookupWithAvailable(LookupRequest lookupRequest);

    List<ReturnAgency> lookupWithCodeWithAvailable(@Param("lookupRequest") LookupRequest lookupRequest, @Param("code") int code);

    List<ReturnAgency> lookupWithDate(@Param("lookupRequest") LookupRequest lookupRequest, @Param("nextDay") LocalDate nextDay);

    List<ReturnAgency> lookupWithCodeWithDate(@Param("lookupRequest") LookupRequest lookupRequest, @Param("code") int code, @Param("nextDay") LocalDate nextDay);

    List<ReturnAgency> lookupWithAvailableWithDate(@Param("lookupRequest") LookupRequest lookupRequest, @Param("nextDay") LocalDate plusDays);

    List<ReturnAgency> lookupWithCodeWithAvailableWithDate(@Param("lookupRequest") LookupRequest lookupRequest, @Param("code") int code, @Param("nextDay") LocalDate nextDay);
}
