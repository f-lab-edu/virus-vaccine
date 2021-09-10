package com.virusvaccine.dto;

import java.time.LocalDateTime;

public class AgencyReservationInfo {

    private final LocalDateTime vaccinateAt;
    private final int vaccineId;
    private final Long restAmount;
    private final Long bookedAmount;

    public AgencyReservationInfo(LocalDateTime vaccinateAt, int vaccineId, Long restAmount, Long bookedAmount) {
        this.vaccinateAt = vaccinateAt;
        this.vaccineId = vaccineId;
        this.restAmount = restAmount;
        this.bookedAmount = bookedAmount;
    }

    public LocalDateTime getVaccinateAt() {
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
