package com.virusvaccine.bookVaccine.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.virusvaccine.bookVaccine.entity.BookingEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.virusvaccine.bookVaccine.entity.QAcquiredVaccineEntity.acquiredVaccineEntity;
import static com.virusvaccine.bookVaccine.entity.QBookingEntity.bookingEntity;
import static com.virusvaccine.user.entity.QAgencyEntity.agencyEntity;
import static com.virusvaccine.vaccine.entity.QVaccineEntity.vaccineEntity;

@Repository
public class BookingRepositoryImpl implements BookingRepositoryCustom{
    private final JPAQueryFactory factory;

    public BookingRepositoryImpl(JPAQueryFactory factory) {
        this.factory = factory;
    }

    public List<BookingEntity> userReservationLookup(Long userId){
        return factory.selectFrom(bookingEntity)
                .fetchJoin()
                .innerJoin(bookingEntity.acquiredVaccine, acquiredVaccineEntity)
                .fetchJoin()
                .innerJoin(acquiredVaccineEntity.agency, agencyEntity)
                .fetchJoin()
                .innerJoin(acquiredVaccineEntity.vaccine, vaccineEntity)
                .where(bookingEntity.user.id.eq(userId))
                .fetch();

    }

    public List<BookingEntity> agencyReservationLookupWithTime(Long agencyId){
        return factory.selectFrom(bookingEntity)
                .fetchJoin()
                .innerJoin(bookingEntity.acquiredVaccine, acquiredVaccineEntity)
                .fetchJoin()
                .innerJoin(acquiredVaccineEntity.vaccine, vaccineEntity)
                .where(acquiredVaccineEntity.agency.id.eq(agencyId))
                .fetch();
    }


}
