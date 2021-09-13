package com.virusvaccine.controller;

import com.virusvaccine.dto.*;
import com.virusvaccine.exception.NotLoginException;
import com.virusvaccine.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api")
public class LookupController {

    private final String userKey = UserController.userKey;

    @Autowired
    private LookupService lookupService;

    @GetMapping("/agency/lookup")
    public ResponseEntity<List<CalculatedReturnedAgency>> lookup(@RequestParam float lat,
                                                                @RequestParam float lng,
                                                                @RequestParam(required = false) VaccineType code,
                                                                @RequestParam(required = false) boolean available,
                                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                                HttpSession session){

        if(session.getAttribute(userKey)==null){
            throw new NotLoginException();
        }

        LookupRequest lookupRequest = new LookupRequest(lat, lng, code, available, date);

        return new ResponseEntity<>(lookupService.lookup(lookupRequest), HttpStatus.OK);

    }

    @GetMapping("/user/reservation/lookup")
    public ResponseEntity<List<UserReservationInfo>> reservation(HttpSession session){

        Long userId = (Long) session.getAttribute(userKey);

        if(userId == null){
            throw new NotLoginException();
        }

        return new ResponseEntity<>(lookupService.lookupReservation(userId), HttpStatus.OK);

    }

    @GetMapping("/agency/reservation/lookup")
    public ResponseEntity<HashMap<LocalDate, CalculatedAgencyReservationInfo>> agencyreservation(HttpSession session){

        Long agencyId = (Long) session.getAttribute(userKey);

        if(agencyId == null){
            throw new NotLoginException();
        }

        return new ResponseEntity<>(lookupService.lookupAgencyReservation(agencyId), HttpStatus.OK);
    }

    @GetMapping("/agency/reservation/time/lookup")
    public ResponseEntity<HashMap<LocalDate, HashMap<Integer, long[]>>> agencyreservationnwithtime(HttpSession session){

        Long agencyId = (Long) session.getAttribute(userKey);

        if(agencyId == null){
            throw new NotLoginException();
        }

        return new ResponseEntity<>(lookupService.lookupAgencyReservationWithTime(agencyId), HttpStatus.OK);
    }


}
