package com.virusvaccine.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

public class VaccineRegistrationRequest {
    @JsonIgnore
    private Long agencyId;
    @NotBlank(message = "백신 ID를 입력해 주세요")
    private final Long vaccineId;
    @NotBlank(message = "백신 수량을 입력해 주세요")
    private final Integer amount;
    @NotBlank(message = "백신 접종 가능 일자를 입력해 주세요")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone="Asia/Seoul")
    private final Timestamp vaccinateAt;

    public VaccineRegistrationRequest( Long vaccineId, Integer amount, Timestamp vaccinateAt) {
        this.vaccineId = vaccineId;
        this.amount = amount;
        this.vaccinateAt = vaccinateAt;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
    }

    public Long getAgencyId() {
        return agencyId;
    }

    public Long getVaccineId() {
        return vaccineId;
    }

    public Integer getAmount() {
        return amount;
    }

    public Timestamp getVaccinateAt() {
        return vaccinateAt;
    }
}
