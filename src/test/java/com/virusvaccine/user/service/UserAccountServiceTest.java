package com.virusvaccine.user.service;

import com.virusvaccine.user.dto.LoginRequest;
import com.virusvaccine.user.dto.User;
import com.virusvaccine.user.dto.UserSignupRequest;
import com.virusvaccine.common.exception.DuplicateUserException;
import com.virusvaccine.common.exception.NoneExistentUserException;
import com.virusvaccine.common.exception.WrongPasswordException;
import com.virusvaccine.user.entity.UserEntity;
import com.virusvaccine.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserAccountServiceTest {
    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserAccountService userAccountService;

    private User user;
    private UserEntity userEntity;
    private UserSignupRequest signupRequest;

    @BeforeEach
    void setUp() {
        user = new User(1L, "abcd@naver.com", "1234", "kim", "01022223333",
                "9804321");
        signupRequest = new UserSignupRequest("random@naver.com", "1234", "1234", "kim", "01033334444",
                "9505261");
        userEntity = new UserEntity(user);
    }

    @Test
    @DisplayName("일반 사용자 회원가입 실패 : 이미 회원가입 되어있는 경우")
    public void signupUserTestWhenCorrectRequestThenSuccess() {
        when(repository.findByEmail(signupRequest.getEmail())).thenReturn(Optional.of(userEntity));

        assertThrows(DuplicateUserException.class, () -> userAccountService.signUp(signupRequest));
    }

    @Test
    @DisplayName("일반 사용자 로그인 실패 : 존재 하지 않는 사용자")
    void userLoginTestWhenNotExistEmailThenFail() {
        LoginRequest loginRequest = new LoginRequest(user.getEmail(), signupRequest.getPassword1(), true);
        when(repository.findByEmail(user.getEmail())).thenReturn(Optional.empty());

        assertThrows(NoneExistentUserException.class, () -> userAccountService.login(loginRequest));

    }

    @Test
    @DisplayName("일반 사용자 로그인 실패 : 비밀번호 불일치")
    void userLoginTestWhenWrongPasswordThenFail() {
        LoginRequest loginRequest = new LoginRequest(user.getEmail(), "FailPassword", true);
        when(repository.findByEmail(user.getEmail())).thenReturn(Optional.of(userEntity));

        assertThrows(WrongPasswordException.class, () -> userAccountService.login(loginRequest));
    }

    @Test
    @DisplayName("일반 사용자 email 중복 : true")
    void validateDuplicateTestWhenExistentEmailThenTrue() {
        when(repository.findByEmail(user.getEmail())).thenReturn(Optional.of(userEntity));
        assertThat(userAccountService.validateDuplicate(user.getEmail()), equalTo(true));
    }

    @Test
    @DisplayName("일반 사용자 email 중복 확인 : false")
    void validateDuplicateTestWhenNonExistentEmailThenFalse() {
        when(repository.findByEmail("NonExistent@test.com")).thenReturn(Optional.empty());

        assertThat(userAccountService.validateDuplicate("NonExistent@test.com"), equalTo(false));
    }

    @Test
    @DisplayName("일반 사용자 가입 성공")
    void signupuserTestWhenCorrectRequestThenSuccess() {
        when(repository.findByEmail(signupRequest.getEmail())).thenReturn(Optional.empty());
        when(repository.save(any(UserEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        userAccountService.signUp(signupRequest);

        verify(repository).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("일반 사용자 가입 실패 : 중복된 이메일")
    void signupuserTestWhenDuplicateEmailThenFail() {
        when(repository.findByEmail(signupRequest.getEmail())).thenReturn(Optional.of(userEntity));

        assertThrows(DuplicateUserException.class, () -> userAccountService.signUp(signupRequest));
    }

    @Test
    @DisplayName("getByEmail 성공")
    public void getUserByEmailTestWhenExistentEmailThenSuccess() {
        when(repository.findByEmail("test@test.com")).thenReturn(Optional.of(userEntity));

        assertThat(userAccountService.getByEmail("test@test.com"), equalTo(userEntity));
    }

    @Test
    @DisplayName("getByEmail 실패 : 존재하지 않는 email")
    public void getUserByEmailTestWhenNoneExistentEmailThenFail() {
        when(repository.findByEmail("FAIL@test.com")).thenReturn(Optional.empty());

        assertThrows(NoneExistentUserException.class, () -> userAccountService.getByEmail("FAIL@test.com"));
    }
}