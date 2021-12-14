package com.virusvaccine.bookVaccine.repository;

import com.virusvaccine.bookVaccine.entity.AcquiredVaccineEntity;
import com.virusvaccine.vaccine.entity.VaccineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

public interface AcquiredVaccineRepository extends JpaRepository<AcquiredVaccineEntity, Long>, AcquiredVaccineRepositoryCustom{
    List<AcquiredVaccineEntity> findByVaccine(VaccineEntity vaccine);

    List<AcquiredVaccineEntity> findByAgency_Id(Long agencyId);

}
