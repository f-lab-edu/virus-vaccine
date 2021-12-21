package com.virusvaccine.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.virusvaccine.user.entity.AgencyEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.virusvaccine.bookVaccine.entity.QAcquiredVaccineEntity.acquiredVaccineEntity;
import static com.virusvaccine.user.entity.QAgencyEntity.agencyEntity;

@Repository
public class AgencyRepositoryImpl implements AgencyRepositoryCustom{
    private final JPAQueryFactory factory;

    public AgencyRepositoryImpl(JPAQueryFactory factory) {
        this.factory = factory;
    }

    public List<AgencyEntity> getAgenciesWithRestAmount(){
        return factory.selectFrom(agencyEntity).innerJoin(agencyEntity, acquiredVaccineEntity.agency)
                .where(acquiredVaccineEntity.restAmount.gt(0))
                .groupBy(agencyEntity)
                .orderBy(acquiredVaccineEntity.restAmount.sum().desc())
                .limit(5)
                .fetch();
    }

    public List<String> getRegionsWithRestAmount(){
        return factory.select(agencyEntity.siDo)
                .from(agencyEntity)
                .innerJoin(agencyEntity, acquiredVaccineEntity.agency)
                .where(acquiredVaccineEntity.restAmount.gt(0))
                .groupBy(agencyEntity.siDo)
                .orderBy(acquiredVaccineEntity.restAmount.sum().desc())
                .limit(5)
                .fetch();
    }
}
