package com.virusvaccine.user.mapper;

import com.virusvaccine.user.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> getByEmail(String userEmail);

    void signup(User user);
}
