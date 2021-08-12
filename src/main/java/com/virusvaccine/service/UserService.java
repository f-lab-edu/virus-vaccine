package com.virusvaccine.service;

import com.virusvaccine.dto.SignupRequest;
import com.virusvaccine.dto.User;
import com.virusvaccine.exception.DuplicateUserException;
import com.virusvaccine.exception.NoneExistentUserException;
import com.virusvaccine.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.SHA256;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    private boolean validateDuplicateUser(String userEmail){

        Optional<User> user = userMapper.getUserByEmail(userEmail);

        return user.isPresent();

    }

    public void signup(SignupRequest signUpRequest) {

        if (validateDuplicateUser(signUpRequest.getEmail())){
            throw new DuplicateUserException();
        }

        User user = new User(1, signUpRequest.getEmail(), SHA256.getSHA(signUpRequest.getPassword1()), signUpRequest.getName(),
                signUpRequest.getPhoneNumber(), signUpRequest.getIdNumber());

        userMapper.signup(user);

    }

    public User getUserByEmail(String userEmail) {

        Optional<User> user = userMapper.getUserByEmail(userEmail);

        if (user.isEmpty()){
            throw new NoneExistentUserException();
        }

        return user.get();

    }


}
