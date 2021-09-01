package com.virusvaccine.controller;

import com.virusvaccine.dto.LookupRequest;
import com.virusvaccine.dto.CalculatedReturnedAgency;
import com.virusvaccine.dto.VaccineType;
import com.virusvaccine.exception.NotLoginException;
import com.virusvaccine.service.AgencyLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/agency")
public class LookupController {

    private final String userKey = UserController.userKey;

    @Autowired
    private AgencyLookupService agencyLookupService;

    @GetMapping("/lookup")
    public ResponseEntity<List<CalculatedReturnedAgency>> lookup(@RequestParam float lat,
                                                                @RequestParam float lng,
                                                                @RequestParam(required = false) VaccineType code,
                                                                @RequestParam(required = false) boolean available,
                                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                                HttpSession session){

        LookupRequest lookupRequest = new LookupRequest(lat, lng, code, available, date);

        if(session.getAttribute(userKey)==null){
            throw new NotLoginException();
        }

        return new ResponseEntity<>(agencyLookupService.lookup(lookupRequest), HttpStatus.OK);

    }
}
