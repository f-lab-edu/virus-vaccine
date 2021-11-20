package com.virusvaccine.lookupStats.controller;

import com.virusvaccine.lookupStats.dto.ReturnedSortedAgency;
import com.virusvaccine.lookupStats.service.LookupStatsService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LookupStatsController {

  private final LookupStatsService lookupStatsService;

  public LookupStatsController(LookupStatsService lookupStatsService) {
    this.lookupStatsService = lookupStatsService;
  }

  @GetMapping("/stats/vaccines/quantity")
  public ResponseEntity<List<Integer>> getQuantityOfVaccines(){ // 각 백신당 확보된 물량 많은순으로 조회

    return new ResponseEntity<>(lookupStatsService.getQuantityOfVaccines(), HttpStatus.OK);

  }

  @GetMapping("/stats/vaccines/quantity/booked")
  public ResponseEntity<List<Integer>> getQuantityOfBookedVaccines(){ // 각 백신당 에약된 수량 많은순으로 조회

    return new ResponseEntity<>(lookupStatsService.getQuantityOfBookedVaccines(), HttpStatus.OK);

  }

  @GetMapping("/stats/agencys/restamount")
  public ResponseEntity<List<ReturnedSortedAgency>> getAgencysWithRestAmount(){  // 남아있는 백신물량이 가장 많은 상위 5개 기관 조회

    return new ResponseEntity<>(lookupStatsService.getAgencysWithRestAmount(), HttpStatus.OK);

  }

  @GetMapping("/stats/regions/restamount")
  public ResponseEntity<List<String>> getRegionsWithRestAmount(){  // 남아있는 백신물량이 가장 많은 상위 5개 지역(시,도) 조회

    return new ResponseEntity<>(lookupStatsService.getRegionsWithRestAmount(), HttpStatus.OK);

  }


}
