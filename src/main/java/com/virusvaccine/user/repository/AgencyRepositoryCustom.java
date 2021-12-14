package com.virusvaccine.user.repository;

import com.virusvaccine.user.entity.AgencyEntity;

import java.util.List;

public interface AgencyRepositoryCustom {
    List<AgencyEntity> getAgenciesWithRestAmount();
    List<String> getRegionsWithRestAmount();
}
