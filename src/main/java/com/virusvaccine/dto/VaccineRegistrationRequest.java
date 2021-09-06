package com.virusvaccine.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

public class VaccineRegistrationRequest {
    @JsonIgnore
    private Long agencyId;
    @NotNull(message = "백신 ID를 입력해 주세요")
    private Long vaccineId;
    @NotNull(message = "백신 수량을 입력해 주세요")
    private Integer amount;
    @NotNull(message = "백신 접종 가능 시간을 입력해 주세요")
    @FutureOrPresent(message = "접종 시간은 과거일 수 없습니다")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime vaccinateAt;

    public VaccineRegistrationRequest() {
    }

    public VaccineRegistrationRequest(Long vaccineId, Integer amount, LocalDateTime vaccinateAt) {
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

    public LocalDateTime getVaccinateAt() {
        return vaccinateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VaccineRegistrationRequest request = (VaccineRegistrationRequest) o;
        return Objects.equals(vaccineId, request.vaccineId) && Objects.equals(amount, request.amount) && Objects.equals(vaccinateAt, request.vaccinateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vaccineId, amount, vaccinateAt);
    }
}
