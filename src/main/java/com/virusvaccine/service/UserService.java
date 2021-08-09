package com.virusvaccine.service;

import com.virusvaccine.dto.User;
import com.virusvaccine.exception.NoneExistentUserException;
import com.virusvaccine.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserByEmail(String userEmail) {

        Optional<User> user = userMapper.getUserByEmail(userEmail);

        if (user.isEmpty()){
            throw new NoneExistentUserException();
        }

        return user.get();
    }
}
