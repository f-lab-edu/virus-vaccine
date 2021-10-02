package com.virusvaccine.lookupAgency.controller;

import com.virusvaccine.common.annotation.Authorized;
import com.virusvaccine.lookupAgency.dto.CalculatedReturnedAgency;
import com.virusvaccine.lookupAgency.dto.LookupRequest;
import com.virusvaccine.lookupAgency.dto.VaccineType;
import com.virusvaccine.user.controller.UserController;
import com.virusvaccine.common.exception.NotLoginException;
import com.virusvaccine.lookupAgency.service.LookupAgencyService;
import static com.virusvaccine.user.service.AccountService.Role.USER;
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

    private final LookupAgencyService lookupAgencyService;

    public LookupAgencyController(LookupAgencyService lookupAgencyService) {
        this.lookupAgencyService = lookupAgencyService;
    }

    @Authorized(USER)
    @GetMapping("/agency")
    public ResponseEntity<List<CalculatedReturnedAgency>> lookup(@RequestParam float lat,
                                                                @RequestParam float lng,
                                                                @RequestParam(required = false) VaccineType code,
                                                                @RequestParam(required = false) boolean available,
                                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                                HttpSession session){

        LookupRequest lookupRequest = new LookupRequest(lat, lng, code, available, date);

        return new ResponseEntity<>(lookupAgencyService.lookup(lookupRequest), HttpStatus.OK);

    }

}
