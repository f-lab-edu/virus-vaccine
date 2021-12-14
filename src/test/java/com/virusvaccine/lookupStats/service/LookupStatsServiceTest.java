package com.virusvaccine.lookupStats.service;

import com.virusvaccine.bookVaccine.entity.AcquiredVaccineEntity;
import com.virusvaccine.bookVaccine.repository.AcquiredVaccineRepository;
import com.virusvaccine.lookupStats.dto.ReturnedSortedAgency;
import com.virusvaccine.user.dto.Agency;
import com.virusvaccine.user.entity.AgencyEntity;
import com.virusvaccine.user.repository.AgencyRepository;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class LookupStatsServiceTest {

    @Mock
    AgencyRepository agencyRepository;

    @Mock
    AcquiredVaccineRepository acquiredVaccineRepository;

    @InjectMocks
    LookupStatsService lookupStatsService;

    VirusEntity virus;
    VaccineEntity vaccine;
    List<Long> answerOfQuantityOfVaccines;
    List<AgencyEntity> agencyEntities;
    List<AcquiredVaccineEntity> acquiredVaccines;


    @BeforeEach
    void setup() {
        answerOfQuantityOfVaccines = List.of(1L, 3L, 4L, 5L, 2L);

        virus = new VirusEntity(new Virus(1L, "COVID", "코로나"));
        vaccine = new VaccineEntity(new Vaccine(1L, "Pf", "화이자", 2, 1L), virus);

        agencyEntities = List.of(
                new AgencyEntity(Agency.builder().id(1L).name("기관1").email("1").password("1").siDo("1").siGunGu("1").eupMyeonDong("1").zipCode("1").address("1").phoneNumber("1").lat(1.0).lng(1.0).build()),
                new AgencyEntity(Agency.builder().id(3L).name("기관3").email("3").password("3").siDo("3").siGunGu("3").eupMyeonDong("3").zipCode("3").address("3").phoneNumber("3").lat(3.0).lng(3.0).build()),
                new AgencyEntity(Agency.builder().id(4L).name("기관4").email("4").password("4").siDo("4").siGunGu("4").eupMyeonDong("4").zipCode("4").address("4").phoneNumber("4").lat(4.0).lng(4.0).build()),
                new AgencyEntity(Agency.builder().id(5L).name("기관5").email("5").password("5").siDo("5").siGunGu("5").eupMyeonDong("5").zipCode("5").address("5").phoneNumber("5").lat(5.0).lng(5.0).build()),
                new AgencyEntity(Agency.builder().id(2L).name("기관2").email("2").password("2").siDo("2").siGunGu("2").eupMyeonDong("2").zipCode("2").address("2").phoneNumber("2").lat(2.0).lng(2.0).build()));
        acquiredVaccines = List.of(
                AcquiredVaccineEntity.AcquiredVaccineEntityBuilder.anAcquiredVaccineEntity().id(1L).vaccine(vaccine).agency(agencyEntities.get(0)).vaccinateAt(Date.valueOf(LocalDate.now())).amount(1).restAmount(1).build(),
                AcquiredVaccineEntity.AcquiredVaccineEntityBuilder.anAcquiredVaccineEntity().id(2L).vaccine(vaccine).agency(agencyEntities.get(1)).vaccinateAt(Date.valueOf(LocalDate.now())).amount(2).restAmount(2).build(),
                AcquiredVaccineEntity.AcquiredVaccineEntityBuilder.anAcquiredVaccineEntity().id(3L).vaccine(vaccine).agency(agencyEntities.get(2)).vaccinateAt(Date.valueOf(LocalDate.now())).amount(3).restAmount(3).build(),
                AcquiredVaccineEntity.AcquiredVaccineEntityBuilder.anAcquiredVaccineEntity().id(4L).vaccine(vaccine).agency(agencyEntities.get(3)).vaccinateAt(Date.valueOf(LocalDate.now())).amount(4).restAmount(4).build(),
                AcquiredVaccineEntity.AcquiredVaccineEntityBuilder.anAcquiredVaccineEntity().id(5L).vaccine(vaccine).agency(agencyEntities.get(4)).vaccinateAt(Date.valueOf(LocalDate.now())).amount(5).restAmount(5).build()
        );
    }


    @Test
    @DisplayName("getQuantityOfVaccines 메서드 단위 테스트")
    public void getQuantityOfVaccinesTest() {
        when(acquiredVaccineRepository.getQuantityOfVaccines())
                .thenReturn(List.of(1L, 3L, 4L, 5L, 2L));

        assertThat(lookupStatsService.getQuantityOfVaccines(), equalTo(answerOfQuantityOfVaccines));

    }

    @Test
    @DisplayName("getQuantityOfBookedVaccines 메서드 단위 테스트")
    public void getQuantityOfBookedVaccinesTest() {

        List<Integer> answer = List.of(5, 4, 3, 2, 1);

        when(acquiredVaccineRepository.getQuantityOfBookedVaccines())
                .thenReturn(answerOfQuantityOfVaccines);

        assertThat(lookupStatsService.getQuantityOfBookedVaccines(), equalTo(answer));
    }

    @Test
    @DisplayName("getAgenciesWithRestAmount 메서드 단위 테스트")
    public void getAgenciesWithRestAmountTest() {

        List<ReturnedSortedAgency> answer = List.of(
                ReturnedSortedAgency.ReturnedSortedAgencyBuilder.aReturnedSortedAgency().withName("기관1").withPhoneNumber("1").withZipCode("1").withSiDo("1").withSiGunGu("1").withEupMyeonDong("1").withAddress("1").build(),
                ReturnedSortedAgency.ReturnedSortedAgencyBuilder.aReturnedSortedAgency().withName("기관3").withPhoneNumber("3").withZipCode("3").withSiDo("3").withSiGunGu("3").withEupMyeonDong("3").withAddress("3").build(),
                ReturnedSortedAgency.ReturnedSortedAgencyBuilder.aReturnedSortedAgency().withName("기관4").withPhoneNumber("4").withZipCode("4").withSiDo("4").withSiGunGu("4").withEupMyeonDong("4").withAddress("4").build(),
                ReturnedSortedAgency.ReturnedSortedAgencyBuilder.aReturnedSortedAgency().withName("기관5").withPhoneNumber("5").withZipCode("5").withSiDo("5").withSiGunGu("5").withEupMyeonDong("5").withAddress("5").build(),
                ReturnedSortedAgency.ReturnedSortedAgencyBuilder.aReturnedSortedAgency().withName("기관2").withPhoneNumber("2").withZipCode("2").withSiDo("2").withSiGunGu("2").withEupMyeonDong("2").withAddress("2").build()
        );

        when(agencyRepository.getAgenciesWithRestAmount())
                .thenReturn(agencyEntities);

        assertThat(lookupStatsService.getAgenciesWithRestAmount(), equalTo(answer));

    }

    @Test
    @DisplayName("getRegionsWithRestAmount 메서드 단위 테스트")
    public void getRegionsWithRestAmountTest() {

        List<String> answer = List.of("기관1", "기관3", "기관4", "기관5", "기관2");

        when(agencyRepository.getRegionsWithRestAmount())
                .thenReturn(List.of("기관1", "기관3", "기관4", "기관5", "기관2"));

        assertThat(lookupStatsService.getRegionsWithRestAmount(), equalTo(answer));

    }

}