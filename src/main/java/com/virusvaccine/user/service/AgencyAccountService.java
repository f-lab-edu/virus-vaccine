package com.virusvaccine.user.service;

import com.virusvaccine.user.dto.Agency;
import com.virusvaccine.user.dto.AgencySignUpRequest;
import com.virusvaccine.user.dto.SignUpRequest;
import com.virusvaccine.common.exception.DuplicateUserException;
import com.virusvaccine.common.exception.NoneExistentUserException;
import com.virusvaccine.user.mapper.AgencyMapper;
import org.springframework.stereotype.Service;
import utils.SHA256;

import java.util.Optional;

@Service
public class AgencyAccountService extends AccountService {
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
    public void signUp(SignUpRequest request) {
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

    public Agency getByEmail(String email) {
        Optional<Agency> agency = mapper.getByEmail(email);

        if (agency.isEmpty()) {
            throw new NoneExistentUserException();
        }
        return agency.get();
    }

    @Override
    protected Role getRole() {
        return Role.AGENCY;
    }
}
