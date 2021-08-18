package com.virusvaccine.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virusvaccine.dto.AgencySignUpRequest;
import com.virusvaccine.dto.LoginRequest;
import com.virusvaccine.dto.UserSignupRequest;
import com.virusvaccine.exception.WrongPasswordException;
import com.virusvaccine.service.AccountServiceFactory;
import com.virusvaccine.service.AgencyAccountService;
import com.virusvaccine.service.UserAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@MockBeans({@MockBean(AgencyAccountService.class), @MockBean(UserAccountService.class), @MockBean(AccountServiceFactory.class)})
class UserControllerTest {
    @Autowired
    private AgencyAccountService agencyAccountService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private AccountServiceFactory accountServiceFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    private void setUp() {

    }

    @Test
    @DisplayName("회원가입 성공 : User LoginRequest")
    void SignupTestWhenCorrectUserSignUpRequestThenSuccess() throws Exception {
        UserSignupRequest signupRequest = new UserSignupRequest("random@naver.com", "1234", "1234", "kim", "01033334444",
                "9505261");
        doNothing().when(userAccountService).signup(any());
        when(accountServiceFactory.getAccountService(signupRequest.isAgency())).thenReturn(userAccountService);

        String content = objectMapper.writeValueAsString(signupRequest);
        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andDo(print());

        verify(userAccountService, times(1)).signup(any());
        verify(userAccountService).signup(signupRequest);
    }

    @Test
    @DisplayName("회원가입 성공 : Agency LoginRequest")
    void SignupTestWhenCorrectAgencySignUpRequestThenSuccess() throws Exception {
        AgencySignUpRequest agencySignUpRequest = AgencySignUpRequest.builder()
                .email("test@test.com").password("1234").validPassword("1234").name("우리내과").phoneNumber("01022223333")
                .zipCode("13338").siDo("경기도").siGunGu("성남시 분당구").eupMyeonDong("백현동").address("123-45번지 201호")
                .lat(37.40205480597614).lng(127.10777810087137)
                .build();
        doNothing().when(agencyAccountService).signup(any());
        when(accountServiceFactory.getAccountService(agencySignUpRequest.isAgency())).thenReturn(agencyAccountService);

        String content = objectMapper.writeValueAsString(agencySignUpRequest);
        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andDo(print());

        verify(agencyAccountService, times(1)).signup(any());
        verify(agencyAccountService).signup(agencySignUpRequest);
    }

    @Test
    @DisplayName("회원가입 실패 : Agency LoginRequest 비밀번호 불일치")
    void SignupTestWhenWrongAgencySignUpRequestThenSuccess() throws Exception {
        AgencySignUpRequest agencySignUpRequest = AgencySignUpRequest.builder()
                .email("test@test.com").password("1234").validPassword("wrongpassword").name("우리내과").phoneNumber("01022223333")
                .zipCode("13338").siDo("경기도").siGunGu("성남시 분당구").eupMyeonDong("백현동").address("123-45번지 201호")
                .lat(37.40205480597614).lng(127.10777810087137)
                .build();
        doNothing().when(agencyAccountService).signup(agencySignUpRequest);
        when(accountServiceFactory.getAccountService(agencySignUpRequest.isAgency())).thenReturn(agencyAccountService);

        String content = objectMapper.writeValueAsString(agencySignUpRequest);
        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().is4xxClientError())
                .andDo(print());

        verify(agencyAccountService, times(0)).signup(any());
    }

    @Test
    @DisplayName("로그인 성공 : User LoginRequest")
    void LoginTestWhenCorrectUserLoginRequestThenSuccess() throws Exception {
        LoginRequest loginRequest = new LoginRequest("test@test.com", "1234", false);
        when(userAccountService.login(loginRequest)).thenReturn(1L);
        when(accountServiceFactory.getAccountService(loginRequest.isAgency())).thenReturn(userAccountService);

        String content = objectMapper.writeValueAsString(loginRequest);
        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andDo(print());

        verify(userAccountService, times(1)).login(any());
        verify(userAccountService).login(loginRequest);
    }

    @Test
    @DisplayName("로그인 성공 : Agency LoginRequest")
    void LoginTestWhenCorrectAgencyLoginRequestThenSuccess() throws Exception {
        LoginRequest loginRequest = new LoginRequest("test@test.com", "1234", true);
        when(agencyAccountService.login(loginRequest)).thenReturn(1L);
        when(accountServiceFactory.getAccountService(loginRequest.isAgency())).thenReturn(agencyAccountService);

        String content = objectMapper.writeValueAsString(loginRequest);
        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andDo(print());

        verify(agencyAccountService, times(1)).login(any());
        verify(agencyAccountService).login(loginRequest);
    }

    @Test
    @DisplayName("로그인 실패 : AgencyLoginRequest")
    void LoginTestWhenWrongAgencyLoginRequestThenSuccess() throws Exception {
        LoginRequest loginRequest = new LoginRequest("test@test.com", "1234", true);
        when(agencyAccountService.login(loginRequest)).thenThrow(new WrongPasswordException());
        when(accountServiceFactory.getAccountService(loginRequest.isAgency())).thenReturn(agencyAccountService);

        String content = objectMapper.writeValueAsString(loginRequest);
        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().is4xxClientError())
                .andDo(print());

        verify(agencyAccountService, times(1)).login(any());
        verify(agencyAccountService).login(loginRequest);
    }

    // TODO: 2021/08/18 logout test 작성
    @Test
    void logout() {
    }

    @Test
    @DisplayName("ObjectMapper 변환 테스트 : AgencySignUpRequest")
    void ObjectMapperTestWithAgencySignUpRequest() throws JsonProcessingException {
        AgencySignUpRequest agencySignUpRequest = AgencySignUpRequest.builder().email("test@test.com").password("1234")
                .validPassword("1234").name("우리내과").zipCode("13338").siDo("경기도").siGunGu("성남시 분당구")
                .eupMyeonDong("백현동").address("123-45번지 201호").lat(37.40205480597614).lng(127.10777810087137)
                .build();
        String content = objectMapper.writeValueAsString(agencySignUpRequest);
        System.out.println(content);
        AgencySignUpRequest jsonAgencySignUpRequest = objectMapper.readValue(content, AgencySignUpRequest.class);

        assertThat(agencySignUpRequest, equalTo(jsonAgencySignUpRequest));
    }

    @Test
    @DisplayName("ObjectMapper 변환 테스트 : LoginRequest")
    void ObjectMapperTestWithLoginRequest() throws JsonProcessingException {
        LoginRequest loginRequest = new LoginRequest("test@test.com", "1234", true);
        String content = objectMapper.writeValueAsString(loginRequest);
        System.out.println(content);
        LoginRequest jsonloginRequest = objectMapper.readValue(content, LoginRequest.class);

        assertThat(loginRequest, equalTo(jsonloginRequest));
    }
}
