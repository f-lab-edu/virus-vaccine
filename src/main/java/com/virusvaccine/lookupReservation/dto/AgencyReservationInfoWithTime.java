package com.virusvaccine.lookupReservation.dto;

import java.time.LocalDateTime;

public class AgencyReservationInfoWithTime {

    private final LocalDateTime vaccinateAt;
    private final int vaccineId;

    public AgencyReservationInfoWithTime(LocalDateTime vaccinateAt, int vaccineId) {
        this.vaccinateAt = vaccinateAt;
        this.vaccineId = vaccineId;
    }

    public LocalDateTime getVaccinateAt() {
        return vaccinateAt;
    }

    public int getVaccineId() {
        return vaccineId;
    }

    @Override
    public String toString() {
        return "AgencyReservationInfoWithTime{" +
                "vaccinateAt=" + vaccinateAt +
                ", vaccineId=" + vaccineId +
                '}';
    }

}
