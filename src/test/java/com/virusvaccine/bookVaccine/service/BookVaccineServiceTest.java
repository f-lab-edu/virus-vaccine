package com.virusvaccine.bookVaccine.service;

import com.virusvaccine.bookVaccine.dto.ReservationRequest;
import com.virusvaccine.bookVaccine.entity.AcquiredVaccineEntity;
import com.virusvaccine.bookVaccine.entity.BookingEntity;
import com.virusvaccine.bookVaccine.entity.VaccinatedStateEntity;
import com.virusvaccine.bookVaccine.repository.AcquiredVaccineRepository;
import com.virusvaccine.bookVaccine.repository.BookingRepository;
import com.virusvaccine.bookVaccine.repository.VaccinatedStateRepository;
import com.virusvaccine.common.exception.DuplicateDateException;
import com.virusvaccine.common.exception.NotFoundException;
import com.virusvaccine.lookupAgency.dto.VaccineType;
import com.virusvaccine.user.dto.Agency;
import com.virusvaccine.user.dto.User;
import com.virusvaccine.user.entity.AgencyEntity;
import com.virusvaccine.user.entity.UserEntity;
import com.virusvaccine.user.repository.UserRepository;
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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BookVaccineServiceTest {

    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private AcquiredVaccineRepository acquiredVaccineRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private VaccinatedStateRepository vaccinatedStateRepository;

    @InjectMocks
    private BookVaccineService bookVaccineService;

    VirusEntity virus;
    VaccineEntity vaccine;
    List<AgencyEntity> agencyEntities;
    List<UserEntity> userEntities;
    List<AcquiredVaccineEntity> acquiredVaccines;
    List<BookingEntity> bookingEntities;
    int year;
    int month;
    int day;
    int hour;
    LocalDate date;

    @BeforeEach
    void setup() {
        date = LocalDate.now();
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
    }

    @Test
    @DisplayName("백신 예약 성공")
    void bookVaccineTestWhenCollectInputThenSuccess() {
        ReservationRequest req = new ReservationRequest(1L, VaccineType.PF);
        when(acquiredVaccineRepository.searchAvailable(1L, 1L, Date.valueOf(date)))
                .thenReturn(Optional.ofNullable(acquiredVaccines.get(0)));
        when(userRepository.getById(1L))
                .thenReturn(userEntities.get(0));

        bookVaccineService.bookVaccine(1L, req);

        verify(bookingRepository).save(any(BookingEntity.class));
        verify(vaccinatedStateRepository).save(new VaccinatedStateEntity(userEntities.get(0), acquiredVaccines.get(0).getVaccine(), 1));
    }

    @Test
    @DisplayName("백신 예약 실패 : 백신 물량 없음")
    void bookVaccineTestWhenEmptyVaccineThrowNotFoundException() {
        ReservationRequest req = new ReservationRequest(1L, VaccineType.PF);
        ReservationRequest req1 = new ReservationRequest(1L, VaccineType.PF);
        when(acquiredVaccineRepository.searchAvailable(1L, 1L, Date.valueOf(date)))
                .thenReturn(Optional.empty());
        when(userRepository.getById(1L))
                .thenReturn(userEntities.get(0));

        assertThrows(NotFoundException.class, () -> bookVaccineService.bookVaccine(1L, req1));
    }

    @Test
    @DisplayName("백신 예약 실패 : 동일 일자 중복된 예약")
    void bookVaccineTestWhenDuplicateDateThrowDuplicateDateException() {
        ReservationRequest req = new ReservationRequest(1L, VaccineType.PF);
        ReservationRequest req1 = new ReservationRequest(1L, VaccineType.PF);
        when(acquiredVaccineRepository.searchAvailable(1L, 1L, Date.valueOf(date)))
                .thenReturn(Optional.ofNullable(acquiredVaccines.get(0)));
        when(userRepository.getById(1L))
                .thenReturn(userEntities.get(0));
        when(bookingRepository.save(any(BookingEntity.class))).thenThrow(DuplicateDateException.class);

        assertThrows(DuplicateDateException.class, () -> bookVaccineService.bookVaccine(1L, req1));
    }
}