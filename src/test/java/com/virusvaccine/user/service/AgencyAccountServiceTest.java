package com.virusvaccine.user.service;

import com.virusvaccine.user.dto.Agency;
import com.virusvaccine.user.dto.AgencySignUpRequest;
import com.virusvaccine.user.dto.LoginRequest;
import com.virusvaccine.common.exception.DuplicateUserException;
import com.virusvaccine.common.exception.NoneExistentUserException;
import com.virusvaccine.common.exception.WrongPasswordException;
import com.virusvaccine.user.entity.AgencyEntity;
import javax.servlet.http.HttpSession;

import com.virusvaccine.user.repository.AgencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.virusvaccine.common.utils.SHA256;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgencyAccountServiceTest {

    @Mock
    private AgencyRepository repository;

    @Mock
    private HttpSession session;

    @InjectMocks
    private AgencyAccountService agencyAccountService;

    private Agency agency;
    private AgencyEntity agencyEntity;
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

        agencyEntity = new AgencyEntity(agency);
    }

    @Test
    @DisplayName("기관 사용자 로그인 성공")
    void agencyLoginTestWhenCorrectRequestThenSuccess() {
        LoginRequest loginRequest = new LoginRequest(agency.getEmail(), agencySignUpRequest.getPassword(), true);
        when(repository.findByEmail(agency.getEmail())).thenReturn(Optional.of(agencyEntity));
        agencyAccountService.session = session;

        agencyAccountService.login(loginRequest);
    }

    @Test
    @DisplayName("기관 사용자 로그인 실패 : 존재 하지 않는 사용자")
    void agencyLoginTestWhenNotExistEmailThenFail() {
        LoginRequest loginRequest = new LoginRequest(agency.getEmail(), agencySignUpRequest.getPassword(), true);
        when(repository.findByEmail(agency.getEmail())).thenReturn(Optional.empty());

        assertThrows(NoneExistentUserException.class, () -> agencyAccountService.login(loginRequest));
    }

    @Test
    @DisplayName("기관 사용자 로그인 실패 : 비밀번호 불일치")
    void agencyLoginTestWhenWrongPasswordThenFail() {
        LoginRequest loginRequest = new LoginRequest(agency.getEmail(), "FailPassword", true);
        when(repository.findByEmail(agency.getEmail())).thenReturn(Optional.of(agencyEntity));

        assertThrows(WrongPasswordException.class, () -> agencyAccountService.login(loginRequest));
    }

    @Test
    @DisplayName("기관 사용자 email 중복 확인 : 중복 존재")
    void validateDuplicateTestWhenExistentEmailThenTrue() {
        when(repository.findByEmail(agency.getEmail())).thenReturn(Optional.of(agencyEntity));
        assertThat(agencyAccountService.validateDuplicate(agency.getEmail()), equalTo(true));
    }

    @Test
    @DisplayName("기관 사용자 email 중복 확인 : 중복 존재")
    void validateDuplicateTestWhenNonExistentEmailThenFalse() {
        when(repository.findByEmail("NonExistent@test.com")).thenReturn(Optional.empty());
        assertThat(agencyAccountService.validateDuplicate("NonExistent@test.com"), equalTo(false));
    }

    @Test
    @DisplayName("기관 사용자 가입 성공")
    void signupAgencyTestWhenCorrectRequestThenSuccess() {
        when(repository.findByEmail(agencySignUpRequest.getEmail())).thenReturn(Optional.empty());
        when(repository.save(any(AgencyEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        agencyAccountService.signUp(agencySignUpRequest);

        verify(repository).save(any(AgencyEntity.class));
    }

    @Test
    @DisplayName("기관 사용자 가입 실패 : 중복된 이메일")
    void signupAgencyTestWhenDuplicateEmailThenFail() {
        when(repository.findByEmail(agencySignUpRequest.getEmail())).thenReturn(Optional.of(agencyEntity));

        assertThrows(DuplicateUserException.class, () -> agencyAccountService.signUp(agencySignUpRequest));
    }

    @Test
    @DisplayName("getByEmail 성공")
    public void getUserByEmailTestWhenExistentEmailThenSuccess() {
        when(repository.findByEmail("test@test.com")).thenReturn(Optional.of(agencyEntity));
        assertThat(agencyAccountService.getByEmail("test@test.com"), equalTo(agencyEntity));
    }

    @Test
    @DisplayName("getByEmail 실패 : 존재하지 않는 email")
    public void getUserByEmailTestWhenNoneExistentEmailThenFail() {
        when(repository.findByEmail("FAIL@test.com")).thenReturn(Optional.empty());
        assertThrows(NoneExistentUserException.class, () -> agencyAccountService.getByEmail("FAIL@test.com"));
    }
}
