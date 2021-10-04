package com.virusvaccine.bookVaccine.controller;

import com.virusvaccine.bookVaccine.dto.ReservationRequest;
import com.virusvaccine.bookVaccine.dto.SearchResult;
import com.virusvaccine.bookVaccine.service.BookVaccineBookingService;
import com.virusvaccine.bookVaccine.service.BookVaccineSearchService;
import com.virusvaccine.common.annotation.AccountId;
import com.virusvaccine.common.annotation.Authorized;
import static com.virusvaccine.user.service.AccountService.Role.USER;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservation")
public class BookVaccineController {

  private final BookVaccineSearchService searchService;
  private final BookVaccineBookingService bookingService;

  public BookVaccineController(
      BookVaccineSearchService searchService,
      BookVaccineBookingService bookingService) {
    this.searchService = searchService;
    this.bookingService = bookingService;
  }

  @Authorized(USER)
  @PutMapping
  public ResponseEntity<Void> bookVaccine(@AccountId Long userId, @RequestBody ReservationRequest request){

    synchronized (this) {
      SearchResult searchResult = searchService.search(request); // 해당 기관에 잔여백신이 있는지 확인
      bookingService.bookVaccine(userId, searchResult); // 백신 예약
    }

    return new ResponseEntity<>(HttpStatus.OK);

  }

}
