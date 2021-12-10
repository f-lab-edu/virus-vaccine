package com.virusvaccine.user.service;

import com.virusvaccine.user.dto.Agency;
import com.virusvaccine.user.dto.AgencySignUpRequest;
import com.virusvaccine.user.dto.SignUpRequest;
import com.virusvaccine.common.exception.DuplicateUserException;
import com.virusvaccine.common.exception.NoneExistentUserException;
import com.virusvaccine.user.entity.AgencyEntity;
import com.virusvaccine.user.mapper.AgencyMapper;
import com.virusvaccine.user.repository.AgencyRepository;
import org.springframework.stereotype.Service;
import com.virusvaccine.common.utils.SHA256;

import java.util.Optional;

@Service
public class AgencyAccountService extends AccountService {

    private final AgencyRepository repository;

    public AgencyAccountService(AgencyRepository repository) {
        this.repository = repository;
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
        AgencyEntity entity = new AgencyEntity(agency);
        repository.save(entity);
    }

    @Override
    public boolean validateDuplicate(String email) {
        Optional<AgencyEntity> agency = repository.findByEmail(email);
        return agency.isPresent();
    }



    public AgencyEntity getByEmail(String email) {
        Optional<AgencyEntity> agency = repository.findByEmail(email);

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
