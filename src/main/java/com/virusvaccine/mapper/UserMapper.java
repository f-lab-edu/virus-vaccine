package com.virusvaccine.mapper;

import com.virusvaccine.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> getUserByEmail(String userEmail);

    void signup(User user);
}
