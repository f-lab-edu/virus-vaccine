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
    UserService userService;

    @PostMapping("/login")
    public User login(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest request){

        User user = userService.getUserByEmail(loginRequest.getUserEmail());
        // 입력한 비밀번호와 디비에 저장된 해당 사용자의 비밀번호가 다르면 예외발생
        if (!user.getUserPassword().equals(loginRequest.getUserPassword())){
            throw new WrongPasswordException();
        }

        HttpSession session = request.getSession();
        session.setAttribute("USER_ID", user.getUserId()); // 세션에 유저 고유식별자로 유저 아이디 저장

        return user;
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        // 로그인 상태가 아니라면 예외 발생
        if (session == null){
            throw new NotLoginException();
        }

        session.invalidate();
    }
}
