package com.virusvaccine.lookupReservation.service;

import com.virusvaccine.bookVaccine.entity.AcquiredVaccineEntity;
import com.virusvaccine.bookVaccine.entity.BookingEntity;
import com.virusvaccine.bookVaccine.repository.AcquiredVaccineRepository;
import com.virusvaccine.bookVaccine.repository.BookingRepository;
import com.virusvaccine.common.exception.NotFoundException;
import com.virusvaccine.lookupReservation.dto.AgencyReservationInfoWithTime;
import com.virusvaccine.lookupReservation.dto.CalculatedAgencyReservationInfo;
import com.virusvaccine.lookupReservation.dto.UserReservationInfo;
import com.virusvaccine.user.dto.Agency;
import com.virusvaccine.user.dto.User;
import com.virusvaccine.user.entity.AgencyEntity;
import com.virusvaccine.user.entity.UserEntity;
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
import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class LookupReservationServiceTest {

    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private AcquiredVaccineRepository acquiredVaccineRepository;

    @InjectMocks
    private LookupReservationService lookupReservationService;

    VirusEntity virus;
    VaccineEntity vaccine;
    List<AgencyEntity> agencyEntities;
    List<UserEntity> userEntities;
    List<AcquiredVaccineEntity> acquiredVaccines;
    List<BookingEntity> bookingEntities;
    List<UserReservationInfo> userReservationInfos;
    int year;
    int month;
    int day;
    int hour;

    @BeforeEach
    void setup() {
        LocalDate date = LocalDate.now();
        year = date.getYear();
        month = date.getMonth().getValue();
        day = date.getDayOfMonth();
        hour = 15;
        virus = new VirusEntity(new Virus(1L, "COVID", "COVID"));
        vaccine = new VaccineEntity(new Vaccine(1L, "Pf", "Pfizer", 2, 1L), virus);

        agencyEntities = List.of(
                new AgencyEntity(new Agency(1L, "1", "1", "1", "1", "1", "1", "1", "1", "1", 37.4031, 127.1101)),
                new AgencyEntity(new Agency(2L, "2", "2", "2", "2", "2", "2", "2", "2", "2", 37.4032, 127.1102)),
                new AgencyEntity(new Agency(3L, "3", "3", "3", "3", "3", "3", "3", "3", "3", 37.4033, 127.1103)));

        acquiredVaccines = List.of(
                AcquiredVaccineEntity.AcquiredVaccineEntityBuilder.anAcquiredVaccineEntity().id(1L).vaccine(vaccine).agency(agencyEntities.get(0)).vaccinateAt(Date.valueOf(LocalDate.now())).amount(2).restAmount(1).build(),
                AcquiredVaccineEntity.AcquiredVaccineEntityBuilder.anAcquiredVaccineEntity().id(2L).vaccine(vaccine).agency(agencyEntities.get(1)).vaccinateAt(Date.valueOf(LocalDate.now())).amount(3).restAmount(2).build(),
                AcquiredVaccineEntity.AcquiredVaccineEntityBuilder.anAcquiredVaccineEntity().id(3L).vaccine(vaccine).agency(agencyEntities.get(2)).vaccinateAt(Date.valueOf(LocalDate.now())).amount(4).restAmount(3).build()
        );

        userEntities = List.of(
                new UserEntity(new User(1L, "1", "1", "1", "1", "1")),
                new UserEntity(new User(2L, "2", "2", "2", "2", "2")),
                new UserEntity(new User(3L, "3", "3", "3", "3", "3"))
        );

        bookingEntities = List.of(
                new BookingEntity(userEntities.get(0), acquiredVaccines.get(0), LocalDateTime.of(year, month, day, hour, 00)),
                new BookingEntity(userEntities.get(1), acquiredVaccines.get(1), LocalDateTime.of(year, month, day, hour, 00)),
                new BookingEntity(userEntities.get(2), acquiredVaccines.get(2), LocalDateTime.of(year, month, day, hour, 00))
        );

        userReservationInfos = List.of(
                UserReservationInfo.UserReservationInfoBuilder.anUserReservationInfo().withVaccineId(vaccine.getId()).withAddress(agencyEntities.get(0).getAddress()).withName(agencyEntities.get(0).getName()).withSiDo(agencyEntities.get(0).getSiDo()).withSiGunGu(agencyEntities.get(0).getSiGunGu()).withEupMyeonDong(agencyEntities.get(0).getEupMyeonDong()).withZipCode(agencyEntities.get(0).getZipCode()).withPhoneNumber(agencyEntities.get(0).getPhoneNumber()).withVaccinateAt(LocalDateTime.of(2021, 12, 15, 15, 0)).build(),
                UserReservationInfo.UserReservationInfoBuilder.anUserReservationInfo().withVaccineId(vaccine.getId()).withAddress(agencyEntities.get(1).getAddress()).withName(agencyEntities.get(1).getName()).withSiDo(agencyEntities.get(0).getSiDo()).withSiGunGu(agencyEntities.get(1).getSiGunGu()).withEupMyeonDong(agencyEntities.get(0).getEupMyeonDong()).withZipCode(agencyEntities.get(1).getZipCode()).withPhoneNumber(agencyEntities.get(1).getPhoneNumber()).withVaccinateAt(LocalDateTime.of(2021, 12, 15, 15, 0)).build(),
                UserReservationInfo.UserReservationInfoBuilder.anUserReservationInfo().withVaccineId(vaccine.getId()).withAddress(agencyEntities.get(2).getAddress()).withName(agencyEntities.get(2).getName()).withSiDo(agencyEntities.get(0).getSiDo()).withSiGunGu(agencyEntities.get(2).getSiGunGu()).withEupMyeonDong(agencyEntities.get(0).getEupMyeonDong()).withZipCode(agencyEntities.get(2).getZipCode()).withPhoneNumber(agencyEntities.get(2).getPhoneNumber()).withVaccinateAt(LocalDateTime.of(2021, 12, 15, 15, 0)).build()
        );
    }

    @Test
    @DisplayName("lookupReservation 메서드 단위 테스트 : 예약한 내역이 있을경우")
    public void lookupReservationFoundTest() {
        UserReservationInfo answer = UserReservationInfo.UserReservationInfoBuilder.anUserReservationInfo().withVaccineId(1L).withName("1").build();

        when(bookingRepository.userReservationLookup(1L))
                .thenReturn(List.of(bookingEntities.get(0)));

        assertThat(lookupReservationService.lookupReservation(1L).get(0).getName(), equalTo(answer.getName()));
    }

    @Test
    @DisplayName("lookupReservation 메서드 단위 테스트 : 예약한 내역이 없는경우")
    public void lookupReservationNotFoundTest() {
        when(bookingRepository.userReservationLookup(1L))
                .thenReturn(List.of());

        assertThrows(NotFoundException.class, () -> lookupReservationService.lookupReservation(1L));
    }

    @Test
    @DisplayName("lookupAgencyReservation 메서드 단위 테스트 : 해당 기관이 확보한 백신 물량이 있는경우")
    public void lookupAgencyReservationTest() {
        HashMap<LocalDate, CalculatedAgencyReservationInfo> answer = new HashMap<>();
        CalculatedAgencyReservationInfo case1 = new CalculatedAgencyReservationInfo();
        case1.getAmount()[0] += 2;
        case1.getBookedAmount()[0] += 1;
        answer.put(LocalDate.of(year, month, day), case1);

        when(acquiredVaccineRepository.findByAgency_Id(1L))
                .thenReturn(List.of(acquiredVaccines.get(0)));

        assertThat(lookupReservationService.lookupAgencyReservation(1L), equalTo(answer));
        System.out.println("passed - " + answer);
    }

    @Test
    @DisplayName("lookupAgencyReservation 메서드 단위 테스트 : 해당 기관이 확보한 백신 물량이 없는경우")
    public void lookupAgencyReservationNotFoundTest() {
        when(acquiredVaccineRepository.findByAgency_Id(1L))
                .thenReturn(List.of());

        assertThrows(NotFoundException.class, () -> lookupReservationService.lookupAgencyReservation(1L));
    }

    @Test
    @DisplayName("lookupAgencyReservationWithTime 메서드 단위 테스트 : 해당 기관이 확보한 백신 물량이 있는경우")
    public void lookupAgencyReservationWithTimeTest() {
        HashMap<LocalDate, HashMap<Integer, long[]>> answer = new HashMap<>();
        HashMap<Integer, long[]> answerCase = new HashMap<>();
        for (int hour = 0; hour < 24; hour++) {
            answerCase.put(hour, new long[5]);
        }
        answerCase.get(15)[0] += 1;
        answer.put(LocalDate.of(year, month, day), answerCase);

        when(bookingRepository.agencyReservationLookupWithTime(1L))
                .thenReturn(List.of(bookingEntities.get(0)));

        HashMap<LocalDate, HashMap<Integer, long[]>> output = lookupReservationService.lookupAgencyReservationWithTime(1L);

        for (Map.Entry<LocalDate, HashMap<Integer, long[]>> entry : output.entrySet()) { // 각 날짜당 시간별 예약수(배열) 비교 검증
            LocalDate key = entry.getKey();
            HashMap<Integer, long[]> partialAnswer = answer.get(key);
            HashMap<Integer, long[]> partialOutput = output.get(key);
            for (int hour = 0; hour < 24; hour++) {
                assertTrue(Arrays.equals(partialAnswer.get(hour), partialOutput.get(hour)));
            }
        }
    }

    @Test
    @DisplayName("lookupAgencyReservationWithTime 메서드 단위 테스트 : 해당 기관이 확보한 백신 물량이 없는경우")
    public void lookupAgencyReservationWithTimeNotFoundTest() {

        when(bookingRepository.agencyReservationLookupWithTime(1L))
                .thenReturn(List.of());

        assertThrows(NotFoundException.class, () -> lookupReservationService.lookupAgencyReservationWithTime(1L));
    }
}
