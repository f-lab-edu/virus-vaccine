package com.virusvaccine.service;

import com.virusvaccine.dto.LoginRequest;
import com.virusvaccine.dto.Member;
import com.virusvaccine.dto.SignUpRequest;

public interface AccountService {
    boolean validateDuplicate(String email);

    void signup(SignUpRequest signUpRequest);

    Long login(LoginRequest request);

    Member getByEmail(String email);
}
