package com.virusvaccine.service;

import com.virusvaccine.dto.Agency;
import com.virusvaccine.dto.AgencySignUpRequest;
import com.virusvaccine.dto.LoginRequest;
import com.virusvaccine.dto.SignUpRequest;
import com.virusvaccine.exception.DuplicateUserException;
import com.virusvaccine.exception.NoneExistentUserException;
import com.virusvaccine.exception.WrongPasswordException;
import com.virusvaccine.mapper.AgencyMapper;
import org.springframework.stereotype.Service;
import utils.SHA256;

import java.util.Optional;

@Service
public class AgencyAccountService implements AccountService<Agency> {
    private final AgencyMapper mapper;

    public AgencyAccountService(AgencyMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean validateDuplicate(String email) {
        Optional<Agency> agency = mapper.getByEmail(email);
        return agency.isPresent();
    }

    @Override
    public void signup(SignUpRequest request) {
        AgencySignUpRequest signUpRequest = (AgencySignUpRequest) request;

        if (validateDuplicate(signUpRequest.getEmail())) {
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

        mapper.signUp(agency);
    }

    @Override
    public Long login(LoginRequest request) {
        Agency agency = getByEmail(request.getUserEmail());
        String password = agency.getPassword();
        Long id = agency.getId();

        if (!password.equals(SHA256.getSHA(request.getUserPassword()))) {
            throw new WrongPasswordException(password, SHA256.getSHA(request.getUserPassword()));
        }

        return id;
    }

    @Override
    public Agency getByEmail(String email) {
        Optional<Agency> agency = mapper.getByEmail(email);

        if (agency.isEmpty()) {
            throw new NoneExistentUserException();
        }
        return agency.get();
    }
}
