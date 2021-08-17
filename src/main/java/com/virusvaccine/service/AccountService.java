package com.virusvaccine.service;

import com.virusvaccine.dto.*;
import com.virusvaccine.exception.DuplicateUserException;
import com.virusvaccine.exception.NoneExistentUserException;
import com.virusvaccine.exception.WrongPasswordException;
import com.virusvaccine.mapper.AgencyMapper;
import com.virusvaccine.mapper.UserMapper;
import org.springframework.stereotype.Service;
import utils.SHA256;

import java.util.Optional;

public interface AccountService <T extends Member> {
     boolean validateDuplicate(String email);

     void signup(SignUpRequest signUpRequest);

    Long login(LoginRequest request);

    T getByEmail(String email);
}
