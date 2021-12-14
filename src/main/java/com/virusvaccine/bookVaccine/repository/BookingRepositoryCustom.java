package com.virusvaccine.bookVaccine.repository;

import com.virusvaccine.bookVaccine.entity.BookingEntity;

import java.util.List;

public interface BookingRepositoryCustom {
    List<BookingEntity> userReservationLookup(Long userId);
    List<BookingEntity> agencyReservationLookupWithTime(Long agencyId);
}
