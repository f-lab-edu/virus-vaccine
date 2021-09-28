package com.virusvaccine.lookupReservation.dto;

import java.time.LocalDate;


public class AgencyReservationInfo {

    private final LocalDate vaccinateAt;
    private final int vaccineId;
    private final Long restAmount;
    private final Long bookedAmount;

    public AgencyReservationInfo(LocalDate vaccinateAt, int vaccineId, Long restAmount, Long bookedAmount) {
        this.vaccinateAt = vaccinateAt;
        this.vaccineId = vaccineId;
        this.restAmount = restAmount;
        this.bookedAmount = bookedAmount;
    }

    public LocalDate getVaccinateAt() {
        return vaccinateAt;
    }

    public int getVaccineId() {
        return vaccineId;
    }

    public Long getRestAmount() {
        return restAmount;
    }

    public Long getBookedAmount() {
        return bookedAmount;
    }

    @Override
    public String toString() {
        return "AgencyReservationInfo{" +
                "vaccinateAt=" + vaccinateAt +
                ", vaccineId=" + vaccineId +
                ", restAmount=" + restAmount +
                ", bookedAmount=" + bookedAmount +
                '}';
    }
}
