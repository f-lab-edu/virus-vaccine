package com.virusvaccine.bookVaccine.controller;

import com.virusvaccine.bookVaccine.dto.ReservationRequest;
import com.virusvaccine.bookVaccine.dto.SearchResult;
import com.virusvaccine.bookVaccine.service.BookVaccineService;
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

  private final BookVaccineService bookingService;

  public BookVaccineController(BookVaccineService bookingService) {
    this.bookingService = bookingService;
  }

  @Authorized(USER)
  @PutMapping
  public ResponseEntity<Void> bookVaccine(@AccountId Long userId, @RequestBody ReservationRequest request){

    SearchResult searchResult = bookingService.searchAvailable(request);

    bookingService.bookVaccine(userId, searchResult); // 백신 예약

    return new ResponseEntity<>(HttpStatus.OK);

  }

}
