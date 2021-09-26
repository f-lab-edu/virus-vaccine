package com.virusvaccine.service;

import com.virusvaccine.dto.SignUpRequest;
import com.virusvaccine.dto.User;
import com.virusvaccine.dto.UserSignupRequest;
import com.virusvaccine.exception.DuplicateUserException;
import com.virusvaccine.exception.NoneExistentUserException;
import com.virusvaccine.mapper.UserMapper;
import org.springframework.stereotype.Service;
import utils.SHA256;

import java.util.Optional;

@Service
public class UserAccountService extends AccountService {
    private final UserMapper mapper;

    public UserAccountService(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean validateDuplicate(String email) {
        Optional<User> agency = mapper.getByEmail(email);
        return agency.isPresent();
    }

    @Override
    public void signUp(SignUpRequest request) {

        UserSignupRequest signUpRequest = (UserSignupRequest) request;

        if (validateDuplicate(signUpRequest.getEmail())) {
            throw new DuplicateUserException();
        }

        User user = new User.Builder()
                .email(signUpRequest.getEmail())
                .password(SHA256.getSHA(signUpRequest.getPassword1()))
                .name(signUpRequest.getName())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .idNumber(signUpRequest.getIdNumber())
                .build();

        mapper.signup(user);

    }

    @Override
    public User getByEmail(String email) {
        Optional<User> user = mapper.getByEmail(email);

        if (user.isEmpty()) {
            throw new NoneExistentUserException();
        }
        return user.get();
    }

    @Override
    protected Role getRole() {
        return Role.USER;
    }
}
