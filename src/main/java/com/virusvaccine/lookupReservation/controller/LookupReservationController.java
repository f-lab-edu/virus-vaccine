package com.virusvaccine.lookupReservation.controller;

import com.virusvaccine.user.controller.UserController;
import com.virusvaccine.lookupReservation.dto.CalculatedAgencyReservationInfo;
import com.virusvaccine.lookupReservation.dto.UserReservationInfo;
import com.virusvaccine.common.exception.NotLoginException;
import com.virusvaccine.lookupReservation.service.LookupReservationService;
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

  @GetMapping("/reservation/user")
  public ResponseEntity<List<UserReservationInfo>> reservation(HttpSession session){

    Long userId = (Long) session.getAttribute(userKey);

    if(userId == null){
      throw new NotLoginException();
    }

    return new ResponseEntity<>(lookupReservationService.lookupReservation(userId), HttpStatus.OK);

  }

  @GetMapping("/reservation/agency")
  public ResponseEntity<HashMap<LocalDate, CalculatedAgencyReservationInfo>> agencyreservation(HttpSession session){

    Long agencyId = (Long) session.getAttribute(userKey);

    if(agencyId == null){
      throw new NotLoginException();
    }

    return new ResponseEntity<>(lookupReservationService.lookupAgencyReservation(agencyId), HttpStatus.OK);
  }

  @GetMapping("/reservation/agency/time")
  public ResponseEntity<HashMap<LocalDate, HashMap<Integer, long[]>>> agencyreservationnwithtime(HttpSession session){

    Long agencyId = (Long) session.getAttribute(userKey);

    if(agencyId == null){
      throw new NotLoginException();
    }

    return new ResponseEntity<>(lookupReservationService.lookupAgencyReservationWithTime(agencyId), HttpStatus.OK);
  }


}
