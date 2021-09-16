package com.virusvaccine.controller;

import com.virusvaccine.dto.LoginRequest;
import com.virusvaccine.dto.SignUpRequest;
import com.virusvaccine.exception.NotIdenticalPasswordException;
import com.virusvaccine.exception.NotLoginException;
import com.virusvaccine.service.AccountService;
import com.virusvaccine.service.AccountServiceFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    static String userKey = "USER_ID";

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
    public ResponseEntity<Void> login(@RequestBody @Valid LoginRequest loginRequest, HttpSession session) {

        AccountService accountService = accountServiceFactory.getAccountService(loginRequest.isAgency());

        Long id = accountService.login(loginRequest);
        session.setAttribute(userKey, id);

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
