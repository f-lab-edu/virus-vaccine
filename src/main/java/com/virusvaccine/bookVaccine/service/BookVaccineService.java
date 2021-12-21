package com.virusvaccine.bookVaccine.service;

import com.virusvaccine.bookVaccine.dto.ReservationRequest;
import com.virusvaccine.bookVaccine.entity.AcquiredVaccineEntity;
import com.virusvaccine.bookVaccine.entity.BookingEntity;
import com.virusvaccine.bookVaccine.entity.VaccinatedStateEntity;
import com.virusvaccine.bookVaccine.mapper.ReservationMapper;
import com.virusvaccine.bookVaccine.repository.AcquiredVaccineRepository;
import com.virusvaccine.bookVaccine.repository.BookingRepository;
import com.virusvaccine.bookVaccine.repository.VaccinatedStateRepository;
import com.virusvaccine.common.exception.DuplicateDateException;
import com.virusvaccine.common.exception.NotFoundException;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import com.virusvaccine.user.entity.UserEntity;
import com.virusvaccine.user.repository.UserRepository;
import com.virusvaccine.vaccine.entity.VaccineEntity;
import com.virusvaccine.vaccine.repository.VaccineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookVaccineService {

  private final ReservationMapper reservationMapper;
  private final AcquiredVaccineRepository acquiredVaccineRepository;
  private final UserRepository userRepository;
  private final BookingRepository bookingRepository;
  private final VaccinatedStateRepository vaccinatedStateRepository;

  public BookVaccineService(
          ReservationMapper reservationMapper, AcquiredVaccineRepository acquiredVaccineRepository, UserRepository userRepository, BookingRepository bookingRepository, VaccinatedStateRepository vaccinatedStateRepository) {
    this.reservationMapper = reservationMapper;
    this.acquiredVaccineRepository = acquiredVaccineRepository;
    this.userRepository = userRepository;
    this.bookingRepository = bookingRepository;
    this.vaccinatedStateRepository = vaccinatedStateRepository;
  }

  @Transactional(isolation = Isolation.SERIALIZABLE)
  public void bookVaccine(Long userId, ReservationRequest request){

    LocalDate today = LocalDate.now();

    Optional<AcquiredVaccineEntity> searchResult = acquiredVaccineRepository.searchAvailable(request.getAgencyId(), request.getVaccineType() == null ? 0L : request.getVaccineType().getType(), Date.valueOf(today));

    if(searchResult.isEmpty()){
      throw new NotFoundException();
    }
    AcquiredVaccineEntity result = searchResult.get();
    result.decreaseRestAmount();
    acquiredVaccineRepository.save(result);

    Random rnd = new Random(); // 접종 가능 시간대 오전 9시 ~ 오후 18시
    LocalDateTime vaccinate_at = result.getVaccinateAt().toLocalDate().atTime(rnd.nextInt(10)+9,0);
    UserEntity user = userRepository.getById(userId);
    BookingEntity booking = new BookingEntity(user, result, vaccinate_at);
    try {
      bookingRepository.save(booking); // 백신예약 기록 저장
    }
    catch (Exception e){
      throw new DuplicateDateException(); // 같은 날짜 예약 방지..
    }
    VaccinatedStateEntity vaccinatedState = new VaccinatedStateEntity(user, result.getVaccine(), 1);
    vaccinatedStateRepository.save(vaccinatedState);

  }


}
