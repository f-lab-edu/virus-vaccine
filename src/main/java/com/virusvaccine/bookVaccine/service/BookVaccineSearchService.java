package com.virusvaccine.bookVaccine.service;

import com.virusvaccine.bookVaccine.dto.ReservationRequest;
import com.virusvaccine.bookVaccine.dto.SearchResult;
import com.virusvaccine.bookVaccine.mapper.ReservationMapper;
import com.virusvaccine.common.exception.NotFoundException;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookVaccineSearchService {

  private final ReservationMapper reservationMapper;

  public BookVaccineSearchService(
      ReservationMapper reservationMapper) {
    this.reservationMapper = reservationMapper;
  }

  public SearchResult search(ReservationRequest request){

    Optional<SearchResult> searchResult = reservationMapper.search(request.getAgencyId(), request.getVaccineType() == null ? 0: request.getVaccineType().getType());

    if (searchResult.isEmpty()){
      throw new NotFoundException();
    }

    return searchResult.get();

  }



}
