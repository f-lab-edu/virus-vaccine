package com.virusvaccine.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class LookupRequest {

    private float lat;

    private float lng;

    private VaccineType code; //백신 코드(브랜드)

    @JsonProperty("isAvailable")
    private boolean isAvailable; // 백신보유 유무(false면 물량 없는 기관도 조희)

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date; // 해당 날짜에(미래) 백신을 보유하고 있는지 보기위함(null 이면 현재 보유물량 보여준다)

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public VaccineType getCode() {
        return code;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "LookupRequest{" +
                "lat=" + lat +
                ", lng=" + lng +
                ", code='" + code + '\'' +
                ", isAvailable=" + isAvailable +
                ", date=" + date +
                '}';
    }
}
