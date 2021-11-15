package com.virusvaccine.bookVaccine.service;

import com.virusvaccine.bookVaccine.dto.ReservationRequest;
import com.virusvaccine.bookVaccine.dto.SearchResult;
import com.virusvaccine.bookVaccine.mapper.ReservationMapper;
import com.virusvaccine.common.exception.DuplicateDateException;
import com.virusvaccine.common.exception.NotFoundException;
import com.zaxxer.hikari.util.IsolationLevel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookVaccineService {

  private final ReservationMapper reservationMapper;

  public BookVaccineService(
      ReservationMapper reservationMapper) {
    this.reservationMapper = reservationMapper;
  }

  @Transactional(isolation = Isolation.SERIALIZABLE)
  public void bookVaccine(Long userId, ReservationRequest request){

    LocalDate today = LocalDate.now();

    Optional<SearchResult> searchResult = reservationMapper.searchAvailable(request.getAgencyId(), request.getVaccineType() == null ? 0 : request.getVaccineType().getType(), today);

    if(searchResult.isEmpty()){
      throw new NotFoundException();
    }

    reservationMapper.decreaseRestAmount(searchResult.get().getId(), searchResult.get().getRestAmount()); // 백신 수량 1개 감소

    Random rnd = new Random(); // 접종 가능 시간대 오전 9시 ~ 오후 18시
    LocalDateTime vaccinate_at = LocalDateTime.of(searchResult.get().getVaccinateAt().getYear(),
        searchResult.get().getVaccinateAt().getMonth(),
        searchResult.get().getVaccinateAt().getDayOfMonth(),
        rnd.nextInt(10)+9,0,0);

    try {
      reservationMapper.bookVaccine(userId, searchResult.get().getId(), vaccinate_at); // 백신예약 기록 저장
    }
    catch (Exception e){
      throw new DuplicateDateException(); // 같은 날짜 예약 방지..
    }

    reservationMapper.updateState(userId, searchResult.get().getVaccineId()); // 해당유저 백신 접종 횟수 업데이트

  }


}
