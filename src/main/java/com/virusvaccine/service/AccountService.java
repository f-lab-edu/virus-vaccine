package com.virusvaccine.service;

import com.virusvaccine.dto.LoginRequest;
import com.virusvaccine.dto.Member;
import com.virusvaccine.dto.SignUpRequest;
import com.virusvaccine.exception.WrongPasswordException;
import utils.SHA256;

public abstract class AccountService {
    public abstract boolean validateDuplicate(String email);

    public abstract void signUp(SignUpRequest signUpRequest);

    public Long login(LoginRequest request) {
        Member member = getByEmail(request.getUserEmail());
        String password = member.getPassword();
        Long id = member.getId();

        if (!password.equals(SHA256.getSHA(request.getUserPassword())))
            throw new WrongPasswordException();

        return id;
    }

    protected abstract Member getByEmail(String email);
}
