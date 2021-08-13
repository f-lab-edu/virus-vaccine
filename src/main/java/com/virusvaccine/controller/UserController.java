package com.virusvaccine.controller;

import com.virusvaccine.dto.AgencySignUpRequest;
import com.virusvaccine.dto.LoginRequest;

import com.virusvaccine.dto.UserSignupRequest;
import com.virusvaccine.dto.User;
import com.virusvaccine.exception.NotIdenticalPasswordException;
import com.virusvaccine.exception.NotLoginException;
import com.virusvaccine.exception.WrongPasswordException;
import com.virusvaccine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.SHA256;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/user")
public class UserController {

    static String userKey = "USER_ID";

    @Autowired
    private UserService userService;

    @PostMapping("/signup/user")
    public void signup(@RequestBody @Valid UserSignupRequest signUpRequest){

        if (!signUpRequest.getPassword1().equals(signUpRequest.getPassword2())){
            throw new NotIdenticalPasswordException();
        }

        userService.signupUser(signUpRequest);

    }

    @PostMapping("/signup/agency")
    public void signup(@RequestBody @Valid AgencySignUpRequest signUpRequest){

        if (!signUpRequest.getPassword().equals(signUpRequest.getValidPassword())){
            throw new NotIdenticalPasswordException();
        }

        userService.signupAgency(signUpRequest);

    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid LoginRequest loginRequest, HttpSession session){
        Long id = userService.login(loginRequest);

        session.setAttribute(userKey, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/logout")
    public void logout(HttpSession session){

        if (session.getAttribute(userKey) == null){
            throw new NotLoginException();
        }

        session.invalidate();

    }

}
