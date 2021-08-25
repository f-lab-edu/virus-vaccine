package com.virusvaccine.controller;

import com.virusvaccine.dto.LookupRequest;
import com.virusvaccine.dto.CalculatedReturnedAgency;
import com.virusvaccine.dto.VaccineType;
import com.virusvaccine.exception.NotLoginException;
import com.virusvaccine.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/agency")
public class LookupController {

    private final String userKey = UserController.userKey;

    @Autowired
    private AgencyService agencyService;

    @GetMapping("/lookup")
    public List<CalculatedReturnedAgency> lookup(@RequestParam float lat,
                                                 @RequestParam float lng,
                                                 @RequestParam(required = false) VaccineType code,
                                                 @RequestParam(required = false) boolean available,
                                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                 HttpSession session){

        LookupRequest lookupRequest = new LookupRequest(lat, lng, code, available, date);

        if(session.getAttribute(userKey)==null){
            throw new NotLoginException();
        }

        return agencyService.lookup(lookupRequest);

    }
}
