package com.virusvaccine.user.service;

import com.virusvaccine.user.dto.Agency;
import com.virusvaccine.user.dto.AgencySignUpRequest;
import com.virusvaccine.user.dto.LoginRequest;
import com.virusvaccine.common.exception.DuplicateUserException;
import com.virusvaccine.common.exception.NoneExistentUserException;
import com.virusvaccine.common.exception.WrongPasswordException;
import com.virusvaccine.user.mapper.AgencyMapper;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.virusvaccine.common.utils.SHA256;

import java.util.Optional;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgencyAccountServiceTest {

    @Mock
    private AgencyMapper agencyMapper;

    @Mock
    private HttpSession session;

    @InjectMocks
    private AgencyAccountService agencyAccountService;

    private Agency agency;
    private AgencySignUpRequest agencySignUpRequest;

    @BeforeEach
    void setUp() {
        agencySignUpRequest = AgencySignUpRequest.builder()
                .email("test@test.com").password("1234").validPassword("1234").name("우리내과").phoneNumber("01022223333")
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
    @DisplayName("기관 사용자 로그인 성공")
    void agencyLoginTestWhenCorrectRequestThenSuccess() {
        LoginRequest loginRequest = new LoginRequest(agency.getEmail(), agencySignUpRequest.getPassword(), true);
        when(agencyMapper.getByEmail(agency.getEmail())).thenReturn(Optional.of(agency));
        agencyAccountService.session = session;

        agencyAccountService.login(loginRequest);

    }

    @Test
    @DisplayName("기관 사용자 로그인 실패 : 존재 하지 않는 사용자")
    void agencyLoginTestWhenNotExistEmailThenFail() {
        LoginRequest loginRequest = new LoginRequest(agency.getEmail(), agencySignUpRequest.getPassword(), true);
        when(agencyMapper.getByEmail(agency.getEmail())).thenReturn(Optional.empty());

        assertThrows(NoneExistentUserException.class, () -> agencyAccountService.login(loginRequest));

    }

    @Test
    @DisplayName("기관 사용자 로그인 실패 : 비밀번호 불일치")
    void agencyLoginTestWhenWrongPasswordThenFail() {
        LoginRequest loginRequest = new LoginRequest(agency.getEmail(), "FailPassword", true);
        when(agencyMapper.getByEmail(agency.getEmail())).thenReturn(Optional.of(agency));

        assertThrows(WrongPasswordException.class, () -> agencyAccountService.login(loginRequest));

    }

    @Test
    @DisplayName("기관 사용자 email 중복 확인 : 중복 존재")
    void validateDuplicateTestWhenExistentEmailThenTrue() {
        when(agencyMapper.getByEmail(agency.getEmail())).thenReturn(Optional.of(agency));
        assertThat(agencyAccountService.validateDuplicate(agency.getEmail()), equalTo(true));
    }

    @Test
    @DisplayName("기관 사용자 email 중복 확인 : 중복 존재")
    void validateDuplicateTestWhenNonExistentEmailThenFalse() {
        when(agencyMapper.getByEmail("NonExistent@test.com")).thenReturn(Optional.empty());
        assertThat(agencyAccountService.validateDuplicate("NonExistent@test.com"), equalTo(false));
    }

    @Test
    @DisplayName("기관 사용자 가입 성공")
    void signupAgencyTestWhenCorrectRequestThenSuccess() {
        when(agencyMapper.getByEmail(agencySignUpRequest.getEmail())).thenReturn(Optional.empty());
        doNothing().when(agencyMapper).signUp(any(Agency.class));

        agencyAccountService.signUp(agencySignUpRequest);

        verify(agencyMapper).signUp(any(Agency.class));
    }

    @Test
    @DisplayName("기관 사용자 가입 실패 : 중복된 이메일")
    void signupAgencyTestWhenDuplicateEmailThenFail() {
        when(agencyMapper.getByEmail(agencySignUpRequest.getEmail())).thenReturn(Optional.of(agency));

        assertThrows(DuplicateUserException.class, () -> agencyAccountService.signUp(agencySignUpRequest));

    }

    @Test
    @DisplayName("getByEmail 성공")
    public void getUserByEmailTestWhenExistentEmailThenSuccess() {
        when(agencyMapper.getByEmail("test@test.com"))
                .thenReturn(Optional.of(agency));
        assertThat(agencyAccountService.getByEmail("test@test.com"), equalTo(agency));
    }

    @Test
    @DisplayName("getByEmail 실패 : 존재하지 않는 email")
    public void getUserByEmailTestWhenNoneExistentEmailThenFail() {
        when(agencyMapper.getByEmail("FAIL@test.com"))
                .thenReturn(Optional.empty());
        assertThrows(NoneExistentUserException.class, () -> agencyAccountService.getByEmail("FAIL@test.com"));
    }
}
