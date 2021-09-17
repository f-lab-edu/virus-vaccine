package com.virusvaccine.controller;

import com.virusvaccine.dto.*;
import com.virusvaccine.exception.NotLoginException;
import com.virusvaccine.service.LookupAgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api")
public class LookupAgencyController {

    private final String userKey = UserController.userKey;

    @Autowired
    private LookupAgencyService lookupAgencyService;

    @GetMapping("/agency")
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

        return new ResponseEntity<>(lookupAgencyService.lookup(lookupRequest), HttpStatus.OK);

    }

}
