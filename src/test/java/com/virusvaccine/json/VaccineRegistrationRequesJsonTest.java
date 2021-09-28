package com.virusvaccine.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virusvaccine.vaccine.dto.VaccineRegistrationRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@JsonTest
public class VaccineRegistrationRequesJsonTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("ObjectMapperTest: VaccineRegistrationRequest ")
    void objectMapperTestWithVaccineRegistrationRequest() throws JsonProcessingException {
        VaccineRegistrationRequest request = new VaccineRegistrationRequest(1L, 10, LocalDate.now().plusDays(1).atStartOfDay());
        String content = objectMapper.writeValueAsString(request);
        System.out.println(content);
        VaccineRegistrationRequest jsonRequest = objectMapper.readValue(content, VaccineRegistrationRequest.class);

        assertThat(request.getVaccineId(), equalTo(jsonRequest.getVaccineId()));
        assertThat(request.getAmount(), equalTo(jsonRequest.getAmount()));
        assertThat(request.getVaccinateAt(), equalTo(jsonRequest.getVaccinateAt()));
    }
}
