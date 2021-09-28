package com.virusvaccine.user.controller;

import com.virusvaccine.user.dto.LoginRequest;
import com.virusvaccine.user.dto.SignUpRequest;
import com.virusvaccine.common.exception.NotIdenticalPasswordException;
import com.virusvaccine.common.exception.NotLoginException;
import com.virusvaccine.user.service.AccountService;
import com.virusvaccine.user.service.AccountServiceFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    public static String userKey = "USER_ID";

    private final AccountServiceFactory accountServiceFactory;

    public UserController(AccountServiceFactory accountServiceFactory) {
        this.accountServiceFactory = accountServiceFactory;
    }

    @PostMapping("/user")
    public ResponseEntity<Void> signupUser(@RequestBody @Valid SignUpRequest signUpRequest) {
        AccountService accountService = accountServiceFactory.getAccountService(signUpRequest.isAgency());

        if (!signUpRequest.validatePassword())
            throw new NotIdenticalPasswordException();
        accountService.signUp(signUpRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO: 2021/08/14 일반 회원 정보 수정
    public ResponseEntity<Void> editUser() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO: 2021/08/14 일반 회원 삭제
    public ResponseEntity<Void> deleteUser() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO: 2021/08/14 기관 회원 정보 수정
    public ResponseEntity<Void> editAgency() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO: 2021/08/14 기관 회원 삭제
    public ResponseEntity<Void> deleteAgency() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid LoginRequest loginRequest) {
        AccountService accountService = accountServiceFactory.getAccountService(loginRequest.isAgency());

        accountService.login(loginRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        if (session.getAttribute(userKey) == null)
            throw new NotLoginException();

        session.invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
