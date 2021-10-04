package com.virusvaccine.bookVaccine.dto;

import java.time.LocalDate;

public class SearchResult {

  private final Long id;
  private final LocalDate vaccinateAt;
  private final int vaccineId;

  public SearchResult(Long id, LocalDate vaccinateAt, int vaccineId) {
    this.id = id;
    this.vaccinateAt = vaccinateAt;
    this.vaccineId = vaccineId;
  }

  public Long getId() {
    return id;
  }

  public LocalDate getVaccinateAt() {
    return vaccinateAt;
  }

  public int getVaccineId() {
    return vaccineId;
  }

  @Override
  public String toString() {
    return "SearchResult{" +
        "id=" + id +
        ", vaccinateAt=" + vaccinateAt +
        ", vaccineId=" + vaccineId +
        '}';
  }

}
