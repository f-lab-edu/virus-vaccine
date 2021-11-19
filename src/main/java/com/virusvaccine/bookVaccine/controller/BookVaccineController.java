package com.virusvaccine.bookVaccine.controller;

import com.virusvaccine.bookVaccine.dto.ReservationRequest;
import com.virusvaccine.bookVaccine.dto.SearchResult;
import com.virusvaccine.bookVaccine.service.BookVaccineService;
import com.virusvaccine.common.annotation.AccountId;
import com.virusvaccine.common.annotation.Authorized;

import static com.virusvaccine.user.service.AccountService.Role.AGENCY;
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

    bookingService.bookVaccine(userId, request);

    return new ResponseEntity<>(HttpStatus.OK);

  }

}
