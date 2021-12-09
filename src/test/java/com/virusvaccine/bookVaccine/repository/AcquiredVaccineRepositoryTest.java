package com.virusvaccine.bookVaccine.repository;

import com.virusvaccine.bookVaccine.entity.AcquiredVaccineEntity;
import com.virusvaccine.vaccine.entity.VaccineEntity;
import com.virusvaccine.vaccine.repository.VaccineRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AcquiredVaccineRepositoryTest {

    @Autowired AcquiredVaccineRepository acquiredVaccineRepository;
    @Autowired VaccineRepository vaccineRepository;

    @Test
    @DisplayName("레포지토리 연결 테스트")
    public void repoTest(){
        List<AcquiredVaccineEntity> list;
        Optional<VaccineEntity> vaccineEntityOptional = vaccineRepository.findById(1L);
        if(vaccineEntityOptional.isPresent()) {
            list = acquiredVaccineRepository.findByVaccine(vaccineEntityOptional.get());
            System.out.println(list.size());
        }

    }
}