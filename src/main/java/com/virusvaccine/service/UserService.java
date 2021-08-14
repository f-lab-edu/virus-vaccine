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

@Service
public class UserService {

    private final UserMapper userMapper;

    private final AgencyMapper agencyMapper;

    public UserService(UserMapper userMapper, AgencyMapper agencyMapper) {
        this.userMapper = userMapper;
        this.agencyMapper = agencyMapper;
    }

    private boolean validateDuplicateUser(String userEmail) {

        Optional<User> user = userMapper.getUserByEmail(userEmail);

        return user.isPresent();

    }

    private boolean validateDuplicateAgency(String userEmail) {

        Optional<Agency> agency = agencyMapper.getAgencyByEmail(userEmail);

        return agency.isPresent();

    }

    public void signupUser(UserSignupRequest signUpRequest) {

        if (validateDuplicateUser(signUpRequest.getEmail())) {
            throw new DuplicateUserException();
        }

        User user = new User.Builder()
                .email(signUpRequest.getEmail())
                .password(SHA256.getSHA(signUpRequest.getPassword1()))
                .name(signUpRequest.getName())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .idNumber(signUpRequest.getIdNumber())
                .build();

        userMapper.signup(user);

    }

    public void signupAgency(AgencySignUpRequest signUpRequest) {

        if (validateDuplicateAgency(signUpRequest.getEmail())) {
            throw new DuplicateUserException();
        }

        Agency agency = new Agency.Builder()
                .email(signUpRequest.getEmail())
                .password(SHA256.getSHA(signUpRequest.getPassword()))
                .name(signUpRequest.getName())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .zipCode(signUpRequest.getZipCode())
                .siDo(signUpRequest.getSiDo())
                .siGunGu(signUpRequest.getSiGunGu())
                .eupMyeonDong(signUpRequest.getEupMyeonDong())
                .address(signUpRequest.getAddress())
                .lat(signUpRequest.getLat())
                .lng(signUpRequest.getLng())
                .build();

        agencyMapper.signUp(agency);

    }

    public Long login(LoginRequest request) {
        String password;
        Long id;

        if (request.isAgency()) {
            Agency agency = getAgencyByEmail(request.getUserEmail());
            password = agency.getPassword();
            id = agency.getId();
        } else {
            User user = getUserByEmail(request.getUserEmail());
            password = user.getPassword();
            id = user.getId();
        }

        if (!password.equals(SHA256.getSHA(request.getUserPassword()))) {
            throw new WrongPasswordException(password, SHA256.getSHA(request.getUserPassword()));
        }

        return id;
    }

    public User getUserByEmail(String userEmail) {
        Optional<User> user = userMapper.getUserByEmail(userEmail);

        if (user.isEmpty()) {
            throw new NoneExistentUserException();
        }
        return user.get();
    }

    public Agency getAgencyByEmail(String email) {
        Optional<Agency> agency = agencyMapper.getAgencyByEmail(email);

        if (agency.isEmpty()) {
            throw new NoneExistentUserException();
        }
        return agency.get();
    }
}
