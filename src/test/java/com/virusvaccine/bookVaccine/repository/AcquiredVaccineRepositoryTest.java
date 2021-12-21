package com.virusvaccine.bookVaccine.repository;

import com.virusvaccine.bookVaccine.entity.AcquiredVaccineEntity;
import com.virusvaccine.lookupAgency.dto.LookupRequest;
import com.virusvaccine.user.entity.AgencyEntity;
import com.virusvaccine.vaccine.entity.VaccineEntity;
import com.virusvaccine.vaccine.repository.VaccineRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AcquiredVaccineRepositoryTest {

    @Autowired AcquiredVaccineRepository acquiredVaccineRepository;
    @Autowired VaccineRepository vaccineRepository;

    @Test
    @DisplayName("레포지토리 연결 테스트")
    public void findByVaccineTest(){
        List<AcquiredVaccineEntity> list = acquiredVaccineRepository.findByAgency_Id(1L);
        System.out.println(list.size());
    }

    @Test
    @DisplayName("searchAvailable 테스트")
    public void searchAvailableTest(){
        Optional<AcquiredVaccineEntity> list = acquiredVaccineRepository.searchAvailable(1L, null, Date.valueOf(LocalDate.of(1990,1,1)));
    }

    @Test
    @DisplayName("lookup 테스트")
    @Transactional
    public void lookupTest(){
        LookupRequest lookupRequest = new LookupRequest(37.403f, 127.110f, null, false, LocalDate.of(2021,12,13));
        List<AcquiredVaccineEntity> list = acquiredVaccineRepository.lookup(lookupRequest);
        AcquiredVaccineEntity a = list.get(0);

        AgencyEntity g = a.getAgency();
    }
}