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
    UserMapper userMapper;

    public User getUserByEmail(String userEmail) {

        Optional<User> user = userMapper.getUserByEmail(userEmail);

        if (user.isEmpty()){  // 존재하지 않는 유저, 회원가입이 안된상태
            throw new NoneExistentUserException();
        }

        return user.get();
    }
}
