package com.virusvaccine.vaccine.repository;

import com.virusvaccine.vaccine.entity.VaccineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VaccineRepository extends JpaRepository<VaccineEntity, Long> {
    Optional<VaccineEntity> findById(Long id);
}
