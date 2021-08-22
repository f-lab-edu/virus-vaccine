package com.virusvaccine.service;

import com.virusvaccine.dto.LoginRequest;
import com.virusvaccine.dto.Member;
import com.virusvaccine.dto.SignUpRequest;
import com.virusvaccine.exception.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import utils.SHA256;

import javax.servlet.http.HttpSession;

public abstract class AccountService {
    public static final String USER_KEY = "USER_ID";
    public static final String ROLE_KEY = "AUTH_ROLE";

    public enum Role{AGENCY, USER}

    @Autowired
    private HttpSession session;

    public abstract boolean validateDuplicate(String email);

    public abstract void signUp(SignUpRequest signUpRequest);

    public void login(LoginRequest request) {
        Member member = getByEmail(request.getUserEmail());
        String password = member.getPassword();
        Long id = member.getId();

        if (!password.equals(SHA256.getSHA(request.getUserPassword())))
            throw new WrongPasswordException();

        session.setAttribute(USER_KEY, id);
        session.setAttribute(ROLE_KEY, getRole());
    }

    protected abstract Member getByEmail(String email);

    protected abstract Role getRole();
}
