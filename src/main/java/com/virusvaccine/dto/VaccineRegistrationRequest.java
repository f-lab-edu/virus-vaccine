package com.virusvaccine.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.virusvaccine.exception.RequestException;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class VaccineRegistrationRequest {
    @JsonIgnore
    private Long agencyId;
    @NotNull(message = "백신 ID를 입력해 주세요")
    private Long vaccineId;
    @NotNull(message = "백신 수량을 입력해 주세요")
    private Integer amount;
    @NotNull(message = "백신 접종 가능 시간을 입력해 주세요")
    @FutureOrPresent(message = "접종 시간은 과거일 수 없습니다")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
    private Timestamp vaccinateAt;

    public VaccineRegistrationRequest() {
    }

    public VaccineRegistrationRequest(Long vaccineId, Integer amount, Timestamp vaccinateAt) {
        this.vaccineId = vaccineId;
        this.amount = amount;
        this.vaccinateAt = vaccinateAt;
    }

    public Long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
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
