package com.virusvaccine.service;

import com.virusvaccine.exception.NoneExistentUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("존재하지 않는 유저 검색시 예외발생 테스트")
    public void NoneExistentUserTest(){
        assertThrows(NoneExistentUserException.class, ()->userService.getUserByEmail("user@email.com"));
    }

}