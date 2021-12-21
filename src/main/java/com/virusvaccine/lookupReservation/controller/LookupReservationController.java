package com.virusvaccine.lookupReservation.controller;

import com.virusvaccine.common.annotation.AccountId;
import com.virusvaccine.common.annotation.Authorized;
import com.virusvaccine.user.controller.UserController;
import com.virusvaccine.lookupReservation.dto.CalculatedAgencyReservationInfo;
import com.virusvaccine.lookupReservation.dto.UserReservationInfo;
import com.virusvaccine.common.exception.NotLoginException;
import com.virusvaccine.lookupReservation.service.LookupReservationService;
import static com.virusvaccine.user.service.AccountService.Role.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LookupReservationController {

  private final String userKey = UserController.userKey;

  private final LookupReservationService lookupReservationService;

  public LookupReservationController(LookupReservationService lookupReservationService) {
    this.lookupReservationService = lookupReservationService;
  }

  @Authorized(USER)
  @GetMapping("/reservation/user")
  public ResponseEntity<List<UserReservationInfo>> reservation(@AccountId Long userId, HttpSession session){

    return new ResponseEntity<>(lookupReservationService.lookupReservation(userId), HttpStatus.OK);
//    클라이언트 -> DTO -> SPRING ->   DTO ->  DB ->   DTO ->   SPRING -> DTO -> Client
//    클라이언트 -> DTO -> SPRING -> Entity -> JPA -> Entity -> SPRING -> DTO -> Client

  }

  @Authorized(AGENCY)
  @GetMapping("/reservation/agency")
  public ResponseEntity<HashMap<LocalDate, CalculatedAgencyReservationInfo>> agencyreservation(@AccountId Long agencyId, HttpSession session){

    return new ResponseEntity<>(lookupReservationService.lookupAgencyReservation(agencyId), HttpStatus.OK);

  }

  @Authorized(AGENCY)
  @GetMapping("/reservation/agency/time")
  public ResponseEntity<HashMap<LocalDate, HashMap<Integer, long[]>>> agencyreservationnwithtime(@AccountId Long agencyId, HttpSession session){

    return new ResponseEntity<>(lookupReservationService.lookupAgencyReservationWithTime(agencyId), HttpStatus.OK);

  }


}
