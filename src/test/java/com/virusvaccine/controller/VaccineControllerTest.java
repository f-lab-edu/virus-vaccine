package com.virusvaccine.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virusvaccine.mapper.VaccineMapper;
import com.virusvaccine.service.VaccineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class VaccineControllerTest {
    @Autowired
    private VaccineService vaccineService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VaccineMapper vaccineMapper;

    // TODO: 2021/08/23 Vaccine 등록 테스트 작성 필요 
    @Test
    void registryVaccine() {

    }
}