package com.virusvaccine.bookVaccine.dto;

import com.virusvaccine.lookupAgency.dto.VaccineType;

public class ReservationRequest {

  private final Long agencyId;
  private final VaccineType vaccineType;

  public ReservationRequest(Long agencyId, VaccineType vaccineType){
    this.agencyId = agencyId;
    this.vaccineType = vaccineType;
  }


  public Long getAgencyId() {
    return agencyId;
  }

  public VaccineType getVaccineType() {
    return vaccineType;
  }

  @Override
  public String toString() {
    return "ReservationRequest{" +
        "agencyId=" + agencyId +
        ", vaccineType=" + vaccineType +
        '}';
  }

}
