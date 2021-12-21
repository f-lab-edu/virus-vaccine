package com.virusvaccine.user.service;

import com.virusvaccine.user.dto.SignUpRequest;
import com.virusvaccine.user.dto.User;
import com.virusvaccine.user.dto.UserSignupRequest;
import com.virusvaccine.common.exception.DuplicateUserException;
import com.virusvaccine.common.exception.NoneExistentUserException;
import com.virusvaccine.user.entity.UserEntity;
import com.virusvaccine.user.mapper.UserMapper;
import com.virusvaccine.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.virusvaccine.common.utils.SHA256;

import java.util.Optional;

@Service
public class UserAccountService extends AccountService {

    private final UserRepository repository;

    public UserAccountService(UserRepository repository) {
        this.repository = repository;
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
        repository.save(new UserEntity(user));
    }

    @Override
    public boolean validateDuplicate(String email) {
        Optional<UserEntity> user = repository.findByEmail(email);
        return user.isPresent();
    }



    @Override
    public UserEntity getByEmail(String email) {
        Optional<UserEntity> user = repository.findByEmail(email);
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
