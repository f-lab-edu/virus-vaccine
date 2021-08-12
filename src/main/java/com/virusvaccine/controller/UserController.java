package com.virusvaccine.controller;

import com.virusvaccine.dto.LoginRequest;

import com.virusvaccine.dto.SignupRequest;
import com.virusvaccine.dto.User;
import com.virusvaccine.exception.NotIdenticalPasswordException;
import com.virusvaccine.exception.NotLoginException;
import com.virusvaccine.exception.WrongPasswordException;
import com.virusvaccine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignupRequest signUpRequest){

        if (!signUpRequest.getPassword1().equals(signUpRequest.getPassword2())){
            throw new NotIdenticalPasswordException();
        }

        userService.signup(signUpRequest);

    }

    @PostMapping("/login")
    public User login(@RequestBody @Valid LoginRequest loginRequest, HttpSession session){

        User user = userService.getUserByEmail(loginRequest.getUserEmail());

        if (!user.getPassword().equals(SHA256.getSHA(loginRequest.getUserPassword()))){
            throw new WrongPasswordException();
        }

        session.setAttribute(userKey, user.getId());

        return user;
    }

    @PutMapping("/logout")
    public void logout(HttpSession session){

        if (session.getAttribute(userKey) == null){
            throw new NotLoginException();
        }

        session.invalidate();

    }

}
