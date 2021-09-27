package com.virusvaccine.vaccine.controller;

import com.virusvaccine.common.annotation.AccountId;
import com.virusvaccine.common.annotation.Authorized;
import com.virusvaccine.vaccine.dto.Vaccine;
import com.virusvaccine.vaccine.dto.VaccineRegistrationRequest;
import com.virusvaccine.vaccine.dto.Virus;
import com.virusvaccine.vaccine.service.VaccineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.virusvaccine.user.service.AccountService.Role.AGENCY;

@RestController
@RequestMapping("/api/vaccines")
public class VaccineController {
    private final VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @GetMapping("/viruses")
    public List<Virus> getViruses() {
        return vaccineService.getViruses();
    }

    @GetMapping
    public List<Vaccine> getVaccines() {
        return vaccineService.getVaccines();
    }

    @Authorized(AGENCY)
    @PostMapping
    public ResponseEntity<Void> registryVaccine(@AccountId Long agencyId, @RequestBody @Valid VaccineRegistrationRequest request) {
        vaccineService.register(agencyId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
