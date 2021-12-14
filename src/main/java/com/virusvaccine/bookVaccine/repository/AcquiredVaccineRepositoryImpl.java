package com.virusvaccine.bookVaccine.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.virusvaccine.bookVaccine.entity.AcquiredVaccineEntity;
import com.virusvaccine.lookupAgency.dto.LookupRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.virusvaccine.bookVaccine.entity.QAcquiredVaccineEntity.acquiredVaccineEntity;

@Repository
public class AcquiredVaccineRepositoryImpl implements AcquiredVaccineRepositoryCustom {
    private final JPAQueryFactory factory;
    private final EntityManager em;

    public AcquiredVaccineRepositoryImpl(JPAQueryFactory factory, EntityManager em) {
        this.factory = factory;
        this.em = em;
    }

    @Override
    public List<AcquiredVaccineEntity> searchAvailable(Long agency_id, Long vaccine_id, Date today) {
        return factory.selectFrom(acquiredVaccineEntity)
                .where(acquiredVaccineEntity.agency.id.eq(agency_id),
                        eqVaccineId(vaccine_id),
                        acquiredVaccineEntity.vaccinateAt.goe(today))
                .fetch();
    }

    private BooleanExpression eqVaccineId(Long vaccine_id) {
        if (vaccine_id == null || vaccine_id == 0L)
            return null;
        return acquiredVaccineEntity.vaccine.id.eq(vaccine_id);
    }

    public List<AcquiredVaccineEntity> lookup(LookupRequest req) {
        String sql = "select v.* " +
                "from acquired_vaccine v join agency a use INDEX (`point_index`) on a.id = v.agency_id " +
                "where 5000 >= st_distance_sphere(lng_lat, POINT("+ req.getLng() + ", "+ req.getLat() +"))";

        if (req.getDate() != null) sql = sql + " and '" + req.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' <= vaccinate_at ";
        else sql = sql + " and now() <= vaccinate_at ";
        if (req.getCode() != null) sql = sql + " and vaccine_id = " + req.getCode().getType();
        if (req.isAvailable()) sql = sql + " and rest_amount > 0";

        return em.createNativeQuery(sql, AcquiredVaccineEntity.class).getResultList();
    }
}
