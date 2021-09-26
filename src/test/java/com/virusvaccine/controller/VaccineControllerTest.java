package com.virusvaccine.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virusvaccine.dto.VaccineRegistrationRequest;
import com.virusvaccine.exception.NotLoginException;
import com.virusvaccine.exception.UnauthorizedException;
import com.virusvaccine.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDate;

import static com.virusvaccine.service.AccountService.SESSION_KEY_ROLE;
import static com.virusvaccine.service.AccountService.SESSION_KEY_USER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VaccineControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("확보 백신 등록 : 성공")
    void registryVaccineTestWhenCorrectRequestThenSuccess() throws Exception {
        VaccineRegistrationRequest request = getGivenRequest(RequestDate.FUTURE);
        String content = objectMapper.writeValueAsString(request);

        perform(content, AccountService.Role.AGENCY, status().isOk(), null);

    }

    @Test
    @DisplayName("확보 백신 등록 : 실패 - 과거 시간으로 등록")
    void registryVaccineTestWhenRequestWithPastDateThenFail() throws Exception {
        VaccineRegistrationRequest request = getGivenRequest(RequestDate.PAST);
        String content = objectMapper.writeValueAsString(request);

        perform(content, AccountService.Role.AGENCY, status().isBadRequest(), MethodArgumentNotValidException.class);
    }

    @Test
    @DisplayName("확보 백신 등록 : 실패 - 로그인 하지 않음")
    void registryVaccineTestWhenGuestRequestThenFail() throws Exception {
        VaccineRegistrationRequest request = getGivenRequest(RequestDate.FUTURE);
        String content = objectMapper.writeValueAsString(request);

        perform(content, null, status().isUnauthorized(), NotLoginException.class);
    }

    @Test
    @DisplayName("확보 백신 등록 : 실패 - user 권한으로 요청")
    void registryVaccineTestWhenUserRequestThenFail() throws Exception {
        VaccineRegistrationRequest request = getGivenRequest(RequestDate.FUTURE);
        String content = objectMapper.writeValueAsString(request);

        perform(content, AccountService.Role.USER, status().isForbidden(), UnauthorizedException.class);
    }

    private VaccineRegistrationRequest getGivenRequest(RequestDate date) {
        VaccineRegistrationRequest request;
        if (date == RequestDate.PAST)
            request = new VaccineRegistrationRequest(1L, 10, LocalDate.now().minusDays(1).atStartOfDay());
        else
            request = new VaccineRegistrationRequest(1L, 10, LocalDate.now().plusDays(1).atStartOfDay());

        return request;
    }

    enum RequestDate {
        PAST, FUTURE;
    }

    private void perform(String content, AccountService.Role role, ResultMatcher httpStatus, Object ex) throws Exception {
        MockHttpServletRequestBuilder post = post("/api/vaccines")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        if (role == AccountService.Role.AGENCY)
            post.sessionAttr(SESSION_KEY_USER, 1L).sessionAttr(SESSION_KEY_ROLE, AccountService.Role.AGENCY);
        else if (role == AccountService.Role.USER)
            post.sessionAttr(SESSION_KEY_USER, 1L).sessionAttr(SESSION_KEY_ROLE, AccountService.Role.USER);

        ResultActions ra = mockMvc.perform(post)
                .andDo(print())
                .andExpect(httpStatus);
        if (ex != null)
            ra.andExpect(r -> assertThat(r.getResolvedException().getClass(), is(ex)));
    }
}