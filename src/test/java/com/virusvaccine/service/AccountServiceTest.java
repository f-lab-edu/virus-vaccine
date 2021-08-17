package com.virusvaccine.service;

import com.virusvaccine.dto.*;
import com.virusvaccine.exception.DuplicateUserException;
import com.virusvaccine.exception.NoneExistentUserException;
import com.virusvaccine.exception.WrongPasswordException;
import com.virusvaccine.mapper.AgencyMapper;
import com.virusvaccine.mapper.UserMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.SHA256;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private AgencyMapper agencyMapper;

    @InjectMocks
    private UserAccountService userAccountService;

    @InjectMocks
    private AgencyAccountService agencyAccountService;

    private User user;
    private Agency agency;
    private AgencySignUpRequest agencySignUpRequest;

    @BeforeEach
    void setUp() {
        user = new User(1L, "abcd@naver.com", "1234", "kim", "010_2222_3333",
                "980432_1");

        agencySignUpRequest = AgencySignUpRequest.builder()
                .email("test@test.com").password("1234").validPassword("1234").name("우리내과")
                .zipCode("13338").siDo("경기도").siGunGu("성남시 분당구").eupMyeonDong("백현동").address("123-45번지 201호")
                .lat(37.40205480597614).lng(127.10777810087137)
                .build();

        agency = Agency.builder()
                .id(1L).email("test@test.com").password(SHA256.getSHA(agencySignUpRequest.getPassword())).name("우리내과")
                .zipCode("13338").siDo("경기도").siGunGu("성남시 분당구").eupMyeonDong("백현동").address("123-45번지 201호")
                .lat(37.40205480597614).lng(127.10777810087137)
                .build();


    }

    @Test
    @DisplayName("getByEmail 메서드 단위 테스트")
    public void getUserByEmailTest() {

        when(userMapper.getByEmail("abcd@naver.com"))
                .thenReturn(Optional.of(user));

        assertThat(userAccountService.getByEmail("abcd@naver.com"), equalTo(user));

        when(userMapper.getByEmail("no@naver.com"))
                .thenReturn(Optional.empty());

        assertThrows(NoneExistentUserException.class, () -> userAccountService.getByEmail("no@naver.com"));

    }

    @Test
    @DisplayName("signup: user 메서드 단위 테스트")
    public void signupUserTest() {
        UserSignupRequest signupRequest = new UserSignupRequest("random@naver.com", "1234", "1234", "kim", "01033334444",
                "9505261");

        when(userMapper.getByEmail(signupRequest.getEmail())).
                thenReturn(Optional.of(user));

        assertThrows(DuplicateUserException.class, () -> userAccountService.signup(signupRequest));

    }

    @Test
    @DisplayName("기관 사용자 가입 성공")
    void signupAgencyTestWhenSuccess() {
        when(agencyMapper.getByEmail(agencySignUpRequest.getEmail())).thenReturn(Optional.empty());
        doNothing().when(agencyMapper).signUp(any(Agency.class));

        agencyAccountService.signup(agencySignUpRequest);

        verify(agencyMapper).signUp(any(Agency.class));
    }

    @Test
    @DisplayName("기관 사용자 가입 실패 : 중복된 이메일")
    void signupAgencyTestWhenFail() {
        when(agencyMapper.getByEmail(agencySignUpRequest.getEmail())).thenReturn(Optional.of(agency));

        assertThrows(DuplicateUserException.class, () -> agencyAccountService.signup(agencySignUpRequest));

        verify(agencyMapper).getByEmail(agencySignUpRequest.getEmail());
    }

    @Test
    @DisplayName("기관 사용자 로그인 성공")
    void loginTestWhenSuccessWithAgency() {
        LoginRequest loginRequest = new LoginRequest(agency.getEmail(), agencySignUpRequest.getPassword(), true);
        when(agencyMapper.getByEmail(agency.getEmail())).thenReturn(Optional.of(agency));

        assertEquals(agencyAccountService.login(loginRequest), agency.getId());

        verify(agencyMapper).getByEmail(agency.getEmail());
    }

    @Test
    @DisplayName("기관 사용자 로그인 실패 : 존재 하지 않는 사용자")
    void loginTestWhenFailWithAgencyBecauseNotExistEmail() {
        LoginRequest loginRequest = new LoginRequest(agency.getEmail(), agencySignUpRequest.getPassword(), true);
        when(agencyMapper.getByEmail(agency.getEmail())).thenReturn(Optional.empty());

        assertThrows(NoneExistentUserException.class, () -> agencyAccountService.login(loginRequest));

        verify(agencyMapper).getByEmail(agency.getEmail());
    }

    @Test
    @DisplayName("기관 사용자 로그인 실패 : 비밀번호 불일치")
    void loginTestWhenFailWithAgencyBecausePasswordError() {
        LoginRequest loginRequest = new LoginRequest(agency.getEmail(), "FailPassword", true);
        when(agencyMapper.getByEmail(agency.getEmail())).thenReturn(Optional.of(agency));

        assertThrows(WrongPasswordException.class, () -> agencyAccountService.login(loginRequest));

        verify(agencyMapper).getByEmail(agency.getEmail());
    }
}
