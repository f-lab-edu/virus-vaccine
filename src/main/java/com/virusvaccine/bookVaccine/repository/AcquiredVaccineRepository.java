package com.virusvaccine.bookVaccine.repository;

import com.virusvaccine.bookVaccine.entity.AcquiredVaccineEntity;
import com.virusvaccine.vaccine.entity.VaccineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcquiredVaccineRepository extends JpaRepository<AcquiredVaccineEntity, Long> {
    List<AcquiredVaccineEntity> findByVaccine(VaccineEntity vaccine);

}
