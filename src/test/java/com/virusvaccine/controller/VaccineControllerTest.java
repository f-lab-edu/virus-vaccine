package com.virusvaccine.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virusvaccine.dto.VaccineRegistrationRequest;
import com.virusvaccine.exception.UnauthorizedException;
import com.virusvaccine.mapper.VaccineMapper;
import com.virusvaccine.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.sql.Timestamp;
import java.time.LocalDate;

import static com.virusvaccine.service.AccountService.SESSION_KEY_ROLE;
import static com.virusvaccine.service.AccountService.SESSION_KEY_USER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VaccineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VaccineMapper vaccineMapper;

    @Test
    @DisplayName("확보 백신 등록 : 성공")
    void registryVaccineTestWhenCorrectRequestThenSuccess() throws Exception {
        VaccineRegistrationRequest request = new VaccineRegistrationRequest(1L, 10, Timestamp.valueOf(LocalDate.now().plusDays(1).atStartOfDay()));
        doNothing().when(vaccineMapper).insertAcquiredVaccine(request);
        String content = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/vaccines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content)
                        .sessionAttr(SESSION_KEY_USER, 1L)
                        .sessionAttr(SESSION_KEY_ROLE, AccountService.Role.AGENCY))
                .andDo(print())
                .andExpect(status().isOk());
        verify(vaccineMapper).insertAcquiredVaccine(any(VaccineRegistrationRequest.class));
    }

    @Test
    @DisplayName("확보 백신 등록 : 실패 - 과거 시간으로 등록")
    void registryVaccineTestWhenCorrectRequestThenFail() throws Exception {
        VaccineRegistrationRequest request = new VaccineRegistrationRequest(1L, 10, Timestamp.valueOf(LocalDate.now().minusDays(1).atStartOfDay()));
        doNothing().when(vaccineMapper).insertAcquiredVaccine(request);
        String content = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/vaccines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content)
                        .sessionAttr(SESSION_KEY_USER, 1L)
                        .sessionAttr(SESSION_KEY_ROLE, AccountService.Role.AGENCY))
                .andDo(print())
                .andExpect(r -> assertThat(r.getResolvedException().getClass(), is(MethodArgumentNotValidException.class)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("확보 백신 등록 : 실패 - 로그인 하지 않음")
    void registryVaccineTestWhenGuestRequestThenFail() throws Exception {
        VaccineRegistrationRequest request = new VaccineRegistrationRequest(1L, 10, Timestamp.valueOf(LocalDate.now().plusDays(1).atStartOfDay()));
        doNothing().when(vaccineMapper).insertAcquiredVaccine(request);
        String content = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/vaccines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(r -> assertThat(r.getResolvedException().getClass(), is(UnauthorizedException.class)))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("확보 백신 등록 : 실패 - user 권한으로 요청")
    void registryVaccineTestWhenUserRequestThenFail() throws Exception {
        VaccineRegistrationRequest request = new VaccineRegistrationRequest(1L, 10, Timestamp.valueOf(LocalDate.now().plusDays(1).atStartOfDay()));
        doNothing().when(vaccineMapper).insertAcquiredVaccine(request);
        String content = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/vaccines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content)
                        .sessionAttr(SESSION_KEY_USER, 1L)
                        .sessionAttr(SESSION_KEY_ROLE, AccountService.Role.USER))
                .andDo(print())
                .andExpect(r -> assertThat(r.getResolvedException().getClass(), is(UnauthorizedException.class)))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("ObjectMapperTest: VaccineRegistrationRequest ")
    void objectMapperTestWithVaccineRegistrationRequest() throws JsonProcessingException {
        VaccineRegistrationRequest request = new VaccineRegistrationRequest(1L, 10, Timestamp.valueOf(LocalDate.now().plusDays(1).atStartOfDay()));
        String content = objectMapper.writeValueAsString(request);
        System.out.println(content);
        VaccineRegistrationRequest jsonRequest = objectMapper.readValue(content, VaccineRegistrationRequest.class);

        assertThat(request.getVaccineId(), equalTo(jsonRequest.getVaccineId()));
        assertThat(request.getAmount(), equalTo(jsonRequest.getAmount()));
        assertThat(request.getVaccinateAt(), equalTo(jsonRequest.getVaccinateAt()));
    }
}