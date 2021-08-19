package com.virusvaccine.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virusvaccine.dto.*;
import com.virusvaccine.exception.*;
import com.virusvaccine.mapper.AgencyMapper;
import com.virusvaccine.mapper.UserMapper;
import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import utils.SHA256;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    AgencyMapper agencyMapper;

    @MockBean
    UserMapper userMapper;

    @BeforeClass
    private void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("회원가입 성공 : User LoginRequest")
    void signupTestWhenCorrectUserSignUpRequestThenSuccess() throws Exception {
        UserSignupRequest userSignUpRequest = new UserSignupRequest("random@naver.com", "1234", "1234", "kim", "01033334444", "9505261");

        when(userMapper.getByEmail(userSignUpRequest.getEmail())).thenReturn(Optional.empty());
        doNothing().when(userMapper).signup(any(User.class));

        String content = objectMapper.writeValueAsString(userSignUpRequest);
        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());

        verify(userMapper).signup(any(User.class));
        verify(userMapper).getByEmail(userSignUpRequest.getEmail());
    }

    @Test
    @DisplayName("회원가입 실패 : User SignUpRequest 비밀번호 불일치")
    void signupTestWhenWrongPasswordUserSignUpRequestThenFail() throws Exception {
        UserSignupRequest wrongUserSignUpRequest = new UserSignupRequest("random@naver.com", "1234", "Wrong", "kim", "01033334444", "9505261");

        String content = objectMapper.writeValueAsString(wrongUserSignUpRequest);
        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(r -> assertThat(r.getResolvedException().getClass(), is(NotIdenticalPasswordException.class)));
    }

    @Test
    @DisplayName("회원가입 실패 : User SignUpRequest 중복된 email")
    void signupTestWhenEmailDuplicatedUserSignUpRequestThenFail() throws Exception {
        User user = new User(1L, "abcd@naver.com", SHA256.getSHA("1234"), "kim", "01022223333", "9804321");
        UserSignupRequest userSignUpRequest = new UserSignupRequest("random@naver.com", "1234", "1234", "kim", "01033334444", "9505261");

        when(userMapper.getByEmail(userSignUpRequest.getEmail())).thenReturn(Optional.of(user));

        String content = objectMapper.writeValueAsString(userSignUpRequest);
        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(r -> assertThat(r.getResolvedException().getClass(), is(DuplicateUserException.class)));
    }

    @Test
    @DisplayName("회원가입 성공 : Agency LoginRequest")
    void signupTestWhenCorrectAgencySignUpRequestThenSuccess() throws Exception {
        AgencySignUpRequest agencySignUpRequest = AgencySignUpRequest.builder()
                .email("test@test.com").password("1234").validPassword("1234").name("우리내과").phoneNumber("01022223333")
                .zipCode("13338").siDo("경기도").siGunGu("성남시 분당구").eupMyeonDong("백현동").address("123-45번지 201호")
                .lat(37.40205480597614).lng(127.10777810087137)
                .build();
        when(agencyMapper.getByEmail(agencySignUpRequest.getEmail())).thenReturn(Optional.empty());
        doNothing().when(agencyMapper).signUp(any(Agency.class));

        String content = objectMapper.writeValueAsString(agencySignUpRequest);
        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 실패 : Agency SignUpRequest 비밀번호 불일치")
    void signupTestWhenWrongAgencySignUpRequestThenFail() throws Exception {
        AgencySignUpRequest wrongAgencySignUpRequest = AgencySignUpRequest.builder()
                .email("test@test.com").password("1234").validPassword("WrongPassword").name("우리내과").phoneNumber("01022223333")
                .zipCode("13338").siDo("경기도").siGunGu("성남시 분당구").eupMyeonDong("백현동").address("123-45번지 201호")
                .lat(37.40205480597614).lng(127.10777810087137)
                .build();

        String content = objectMapper.writeValueAsString(wrongAgencySignUpRequest);
        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(r -> assertThat(r.getResolvedException().getClass(), is(NotIdenticalPasswordException.class)));
    }

    @Test
    @DisplayName("회원가입 실패 : Agency SignUpRequest 중복된 email")
    void signupTestWhenEmailDuplicatedAgencySignUpRequestThenFail() throws Exception {
        AgencySignUpRequest agencySignUpRequest = AgencySignUpRequest.builder()
                .email("test@test.com").password("1234").validPassword("1234").name("우리내과").phoneNumber("01022223333")
                .zipCode("13338").siDo("경기도").siGunGu("성남시 분당구").eupMyeonDong("백현동").address("123-45번지 201호")
                .lat(37.40205480597614).lng(127.10777810087137)
                .build();
        Agency agency = Agency.builder()
                .id(1L).email("test@test.com").password(SHA256.getSHA(agencySignUpRequest.getPassword())).name("우리내과")
                .zipCode("13338").siDo("경기도").siGunGu("성남시 분당구").eupMyeonDong("백현동").address("123-45번지 201호")
                .lat(37.40205480597614).lng(127.10777810087137)
                .build();
        when(agencyMapper.getByEmail(agencySignUpRequest.getEmail())).thenReturn(Optional.of(agency));

        String content = objectMapper.writeValueAsString(agencySignUpRequest);
        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(r -> assertThat(r.getResolvedException().getClass(), is(DuplicateUserException.class)));
    }

    @Test
    @DisplayName("로그인 성공 : User LoginRequest")
    void loginTestWhenCorrectUserLoginRequestThenSuccess() throws Exception {
        LoginRequest userLoginRequest = new LoginRequest("random@naver.com", "1234", false);
        User user = new User(1L, "abcd@naver.com", SHA256.getSHA("1234"), "kim", "01022223333", "9804321");

        when(userMapper.getByEmail(userLoginRequest.getUserEmail())).thenReturn(Optional.of(user));

        String content = objectMapper.writeValueAsString(userLoginRequest);
        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인 성공 : Agency LoginRequest")
    void loginTestWhenCorrectAgencyLoginRequestThenSuccess() throws Exception {
        Agency agency = Agency.builder()
                .id(1L).email("test@test.com").password(SHA256.getSHA("1234")).name("우리내과")
                .zipCode("13338").siDo("경기도").siGunGu("성남시 분당구").eupMyeonDong("백현동").address("123-45번지 201호")
                .lat(37.40205480597614).lng(127.10777810087137)
                .build();
        LoginRequest agencyLoginRequest = new LoginRequest(agency.getEmail(), "1234", true);
        when(agencyMapper.getByEmail(agencyLoginRequest.getUserEmail())).thenReturn(Optional.of(agency));

        String content = objectMapper.writeValueAsString(agencyLoginRequest);
        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인 실패 : AgencyLoginRequest 존재하지 않는 사용자")
    void loginTestWhenNotExistEmailAgencyLoginRequestThenFail() throws Exception {
        LoginRequest agencyLoginRequest = new LoginRequest("WrongEmail@test.com", "1234", true);
        when(agencyMapper.getByEmail(agencyLoginRequest.getUserEmail())).thenReturn(Optional.empty());

        String content = objectMapper.writeValueAsString(agencyLoginRequest);
        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(r -> assertThat(r.getResolvedException().getClass(), is(NoneExistentUserException.class)));
    }

    @Test
    @DisplayName("로그인 실패 : AgencyLoginRequest 비밀번호 불일치")
    void loginTestWhenWrongPasswordAgencyLoginRequestThenFail() throws Exception {
        Agency agency = Agency.builder()
                .id(1L).email("test@test.com").password(SHA256.getSHA("1234")).name("우리내과")
                .zipCode("13338").siDo("경기도").siGunGu("성남시 분당구").eupMyeonDong("백현동").address("123-45번지 201호")
                .lat(37.40205480597614).lng(127.10777810087137)
                .build();
        LoginRequest agencyLoginRequest = new LoginRequest(agency.getEmail(), "WrongPassword", true);
        when(agencyMapper.getByEmail(agencyLoginRequest.getUserEmail())).thenReturn(Optional.of(agency));

        String content = objectMapper.writeValueAsString(agencyLoginRequest);
        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(r -> assertThat(r.getResolvedException().getClass(), is(WrongPasswordException.class)));
    }

    @Test
    @DisplayName("로그아웃 성공 ")
    void logoutTestWhenCorrectRequestThenSuccess() throws Exception {
        MockHttpSession session = new MockHttpSession();

        session.setAttribute("USER_ID", 1);

        mockMvc.perform(put("/api/logout")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그아웃 실패 : seesion 부재")
    void logoutTestWhenWrongRequestThenSuccess() throws Exception {

        mockMvc.perform(put("/api/logout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(r -> assertThat(r.getResolvedException().getClass(), is(NotLoginException.class)));
    }

    @Test
    @DisplayName("ObjectMapper 변환 테스트 : AgencySignUpRequest")
    void objectMapperTestWithAgencySignUpRequest() throws JsonProcessingException {
        AgencySignUpRequest agencySignUpRequest = AgencySignUpRequest.builder()
                .email("test@test.com").password("1234").validPassword("1234").name("우리내과").phoneNumber("01022223333")
                .zipCode("13338").siDo("경기도").siGunGu("성남시 분당구").eupMyeonDong("백현동").address("123-45번지 201호")
                .lat(37.40205480597614).lng(127.10777810087137)
                .build();

        String content = objectMapper.writeValueAsString(agencySignUpRequest);
        System.out.println(content);
        AgencySignUpRequest jsonAgencySignUpRequest = objectMapper.readValue(content, AgencySignUpRequest.class);

        assertThat(agencySignUpRequest, equalTo(jsonAgencySignUpRequest));
    }


    @Test
    @DisplayName("ObjectMapper 변환 테스트 : UserSignUpRequest")
    void objectMapperTestWithUserSignUpRequest() throws JsonProcessingException {
        UserSignupRequest userSignUpRequest = new UserSignupRequest("random@naver.com", "1234", "1234", "kim", "01033334444", "9505261");

        String content = objectMapper.writeValueAsString(userSignUpRequest);
        System.out.println(content);
        UserSignupRequest jsonUserSignupRequest = objectMapper.readValue(content, UserSignupRequest.class);

        assertThat(userSignUpRequest, equalTo(jsonUserSignupRequest));
    }

    @Test
    @DisplayName("ObjectMapper 변환 테스트 : LoginRequest")
    void objectMapperTestWithLoginRequest() throws JsonProcessingException {
        LoginRequest loginRequest = new LoginRequest("test@test.com", "1234", true);
        String content = objectMapper.writeValueAsString(loginRequest);
        System.out.println(content);
        LoginRequest jsonloginRequest = objectMapper.readValue(content, LoginRequest.class);

        assertThat(loginRequest, equalTo(jsonloginRequest));
    }
}
