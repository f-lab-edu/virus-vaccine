package com.virusvaccine.bookVaccine.repository;

import com.virusvaccine.bookVaccine.entity.BookingEntity;
import com.virusvaccine.bookVaccine.entity.BookingEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, BookingEntityPK>, BookingRepositoryCustom{

}
