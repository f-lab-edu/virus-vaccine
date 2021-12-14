package com.virusvaccine.bookVaccine.repository;

import com.virusvaccine.bookVaccine.entity.VaccinatedStateEntity;
import com.virusvaccine.bookVaccine.entity.VaccinatedStateEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinatedStateRepository extends JpaRepository<VaccinatedStateEntity, VaccinatedStateEntityPK> {

}
