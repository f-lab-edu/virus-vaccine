package com.virusvaccine.lookupAgency.service;

import com.virusvaccine.bookVaccine.entity.AcquiredVaccineEntity;
import com.virusvaccine.bookVaccine.repository.AcquiredVaccineRepository;
import com.virusvaccine.common.exception.NotFoundException;
import com.virusvaccine.lookupAgency.dto.CalculatedReturnedAgency;
import com.virusvaccine.lookupAgency.dto.LookupRequest;
import com.virusvaccine.user.dto.Agency;
import com.virusvaccine.user.entity.AgencyEntity;
import com.virusvaccine.vaccine.dto.Vaccine;
import com.virusvaccine.vaccine.dto.Virus;
import com.virusvaccine.vaccine.entity.VaccineEntity;
import com.virusvaccine.vaccine.entity.VirusEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LookupAgencyServiceTest {

    @Mock
    private AcquiredVaccineRepository acquiredVaccineRepository;

    @InjectMocks
    private LookupAgencyService lookupAgencyService;

    private LookupRequest lookupRequest;
    private AgencyEntity agency1, agency2, agency3;
    private VaccineEntity vaccine;
    private VirusEntity virus;
    private List<AcquiredVaccineEntity> acquiredVaccines;
    private List<CalculatedReturnedAgency> answer;

    @BeforeEach
    private void setUp(){
        lookupRequest = new LookupRequest((float) 37.403, (float) 127.110, null, true, LocalDate.now());
        virus = new VirusEntity(new Virus(1L, "COVID", "코로나"));
        vaccine = new VaccineEntity(new Vaccine(1L, "Pf", "화이자", 2, 1L), virus);
        agency1 = new AgencyEntity(new Agency(1L, "1", "1", "1", "1","1", "1", "1", "1", "1", 37.4031,  127.1101));
        agency2 = new AgencyEntity(new Agency(2L, "2", "2", "2", "2","2", "2", "2", "2", "2", 37.4032,  127.1102));
        agency3 = new AgencyEntity(new Agency(3L, "3", "3", "3", "3","3", "3", "3", "3", "3", 37.4033,  127.1103));
        acquiredVaccines = List.of(
                AcquiredVaccineEntity.AcquiredVaccineEntityBuilder.anAcquiredVaccineEntity().id(1L).vaccine(vaccine).agency(agency1).vaccinateAt(Date.valueOf(LocalDate.now())).amount(1).restAmount(1).build(),
                AcquiredVaccineEntity.AcquiredVaccineEntityBuilder.anAcquiredVaccineEntity().id(2L).vaccine(vaccine).agency(agency2).vaccinateAt(Date.valueOf(LocalDate.now())).amount(2).restAmount(2).build(),
                AcquiredVaccineEntity.AcquiredVaccineEntityBuilder.anAcquiredVaccineEntity().id(3L).vaccine(vaccine).agency(agency3).vaccinateAt(Date.valueOf(LocalDate.now())).amount(3).restAmount(3).build()
                );
        answer = List.of(
                CalculatedReturnedAgency.CalculatedReturnedAgencyBuilder.aCalculatedReturnedAgency().withId(1L).withPhoneNumber("1").withZipCode("1").withSiDo("1").withSiGunGu("1").withEupMyeonDong("1").withAddress("1").withName("1").build(),
                CalculatedReturnedAgency.CalculatedReturnedAgencyBuilder.aCalculatedReturnedAgency().withId(2L).withPhoneNumber("2").withZipCode("2").withSiDo("2").withSiGunGu("2").withEupMyeonDong("2").withAddress("2").withName("2").build(),
                CalculatedReturnedAgency.CalculatedReturnedAgencyBuilder.aCalculatedReturnedAgency().withId(3L).withPhoneNumber("3").withZipCode("3").withSiDo("3").withSiGunGu("3").withEupMyeonDong("3").withAddress("3").withName("3").build());
        answer.get(0).getRestAmount()[0] += 1;
        answer.get(0).addTotal(1);
        answer.get(1).getRestAmount()[0] += 2;
        answer.get(1).addTotal(2);
        answer.get(2).getRestAmount()[0] += 3;
        answer.get(2).addTotal(3);

    }

    @Test
    @DisplayName("lookup 메서드 단위 테스트 : 조건에 맞는 기관을 찾았을때")
    public void lookupFoundTest(){
        when(acquiredVaccineRepository.lookup(lookupRequest))
                .thenReturn(acquiredVaccines);

        assertThat(lookupAgencyService.lookup(lookupRequest), equalTo(answer));

    }

    @Test
    @DisplayName("lookup 메서드 단위 테스트 : 조건에 맞는 기관을 못찾았을때")
    public void lookupNotFoundTest(){

        when(acquiredVaccineRepository.lookup(lookupRequest))
                .thenReturn(List.of());

        assertThrows(NotFoundException.class, () -> lookupAgencyService.lookup(lookupRequest));

    }

}