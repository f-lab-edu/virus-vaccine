package com.virusvaccine.service;

import com.virusvaccine.dto.SignupRequest;
import com.virusvaccine.dto.User;
import com.virusvaccine.exception.DuplicateUserException;
import com.virusvaccine.exception.NoneExistentUserException;
import com.virusvaccine.mapper.UserMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("getUserByEmail 메서드 단위 테스트")
    public void getUserByEmailTest(){

        User user = new User(1, "abcd@naver.com", "1234", "kim", "01022223333",
                "9804321");

        when(userMapper.getUserByEmail("abcd@naver.com"))
                .thenReturn(Optional.of(user));

        assertThat(userService.getUserByEmail("abcd@naver.com"), equalTo(user));

        when(userMapper.getUserByEmail("no@naver.com"))
                .thenReturn(Optional.ofNullable(null));

        assertThrows(NoneExistentUserException.class, ()->userService.getUserByEmail("no@naver.com"));

    }

    @Test
    @DisplayName("signup 메서드 단위 테스트")
    public void signupTest(){

        SignupRequest signupRequest = new SignupRequest("random@naver.com", "1234", "1234", "kim", "01033334444",
                "9505261");
        User user = new User(1, "random@naver.com", "1234", "kim", "01033334444",
                "9505261");

        when(userMapper.getUserByEmail(signupRequest.getEmail())).
                thenReturn(Optional.of(user));

        assertThrows(DuplicateUserException.class, ()->userService.signup(signupRequest));

    }

}
