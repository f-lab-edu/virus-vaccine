package com.virusvaccine.bookVaccine.dto;

import java.time.LocalDate;
import java.util.Objects;

public class SearchResult {

  private final Long id;
  private final int vaccineId;
  private final LocalDate vaccinateAt;
  private final Long restAmount;

  public SearchResult(Long id, int vaccineId, LocalDate vaccinateAt, Long restAmount) {
    this.id = id;
    this.vaccineId = vaccineId;
    this.vaccinateAt = vaccinateAt;
    this.restAmount = restAmount;
  }

  public Long getId() {
    return id;
  }

  public int getVaccineId() {
    return vaccineId;
  }

  public LocalDate getVaccinateAt() {
    return vaccinateAt;
  }

  public Long getRestAmount() {
    return restAmount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SearchResult)) {
      return false;
    }
    SearchResult that = (SearchResult) o;
    return vaccineId == that.vaccineId && Objects.equals(id, that.id)
        && Objects.equals(vaccinateAt, that.vaccinateAt) && Objects.equals(
        restAmount, that.restAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vaccineId, vaccinateAt, restAmount);
  }

  @Override
  public String toString() {
    return "SearchResult{" +
        "id=" + id +
        ", vaccineId=" + vaccineId +
        ", vaccinateAt=" + vaccinateAt +
        ", restAmount=" + restAmount +
        '}';
  }

}
