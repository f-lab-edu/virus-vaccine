package com.virusvaccine.bookVaccine.service;

import com.virusvaccine.bookVaccine.dto.ReservationRequest;
import com.virusvaccine.bookVaccine.dto.SearchResult;
import com.virusvaccine.bookVaccine.mapper.ReservationMapper;
import com.virusvaccine.common.exception.DuplicateDateException;
import com.virusvaccine.common.exception.NotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookVaccineService {

  private final ReservationMapper reservationMapper;

  public BookVaccineService(
      ReservationMapper reservationMapper) {
    this.reservationMapper = reservationMapper;
  }

  public SearchResult searchAvailable(ReservationRequest request) {

    LocalDate today = LocalDate.now();

    Optional<SearchResult> searchResult = reservationMapper.searchAvailable(request.getAgencyId(), request.getVaccineType() == null ? 0 : request.getVaccineType().getType(), today);

    if(searchResult.isEmpty()){
      throw new NotFoundException();
    }

    return searchResult.get();

  }

  @Transactional
  public void bookVaccine(Long userId, SearchResult searchResult){

    int rowCount = reservationMapper.decreaseRestAmount(searchResult.getId(), searchResult.getRestAmount()); // 백신 수량 1개 감소

    if(rowCount == 0){
      throw new NotFoundException();
    }

    Random rnd = new Random(); // 접종 가능 시간대 오전 9시 ~ 오후 18시
    LocalDateTime vaccinate_at = LocalDateTime.of(searchResult.getVaccinateAt().getYear(),
        searchResult.getVaccinateAt().getMonth(),
        searchResult.getVaccinateAt().getDayOfMonth(),
        rnd.nextInt(10)+9,0,0);

    try {
      reservationMapper.bookVaccine(userId, searchResult.getId(), vaccinate_at); // 백신예약 기록 저장
    }
    catch (Exception e){
      throw new DuplicateDateException();
    }

    reservationMapper.updateState(userId, searchResult.getVaccineId()); // 해당유저 백신 접종 횟수 업데이트

  }

}
