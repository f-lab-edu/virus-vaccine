package com.virusvaccine.bookVaccine.service;

import com.virusvaccine.bookVaccine.dto.SearchResult;
import com.virusvaccine.bookVaccine.mapper.ReservationMapper;
import java.time.LocalDateTime;
import java.util.Random;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookVaccineBookingService {

  private final ReservationMapper reservationMapper;

  public BookVaccineBookingService(
      ReservationMapper reservationMapper) {
    this.reservationMapper = reservationMapper;
  }

  @Transactional
  public void bookVaccine(Long userId, SearchResult searchResult) {

    reservationMapper.decreaseRestAmount(searchResult.getId()); // 백신 수량 1개 감소

    Random rnd = new Random(); // 접종 가능 시간대 오전 9시 ~ 오후 18시
    LocalDateTime vaccinate_at = LocalDateTime.of(searchResult.getVaccinateAt().getYear(),
        searchResult.getVaccinateAt().getMonth(),
        searchResult.getVaccinateAt().getDayOfMonth(),
        rnd.nextInt(10)+9,0,0);

    reservationMapper.bookVaccine(userId, searchResult.getId(), vaccinate_at); // 백신예약 기록 저장
    reservationMapper.updateState(userId, searchResult.getVaccineId()); // 해당유저 백신 접종 횟수 업데이트

  }

}
