package com.virusvaccine.controller;

import com.virusvaccine.dto.LoginRequest;

import com.virusvaccine.dto.User;
import com.virusvaccine.exception.NotLoginException;
import com.virusvaccine.exception.WrongPasswordException;
import com.virusvaccine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User login(@RequestBody @Valid LoginRequest loginRequest, HttpSession session){

        User user = userService.getUserByEmail(loginRequest.getUserEmail());

        if (!user.getUserPassword().equals(loginRequest.getUserPassword())){
            throw new WrongPasswordException();
        }

        session.setAttribute("USER_ID", user.getUserId());

        return user;
    }

    @PutMapping("/logout")
    public void logout(HttpSession session){

        if (session.getAttribute("USER_ID") == null){
            throw new NotLoginException();
        }

        session.invalidate();

    }

}
