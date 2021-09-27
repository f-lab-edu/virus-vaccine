package com.virusvaccine.user.service;

import org.springframework.stereotype.Service;

@Service
public class AccountServiceFactory {
    private UserAccountService userAccountService;
    private AgencyAccountService agencyAccountService;

    public AccountServiceFactory(UserAccountService userAccountService, AgencyAccountService agencyAccountService) {
        this.userAccountService = userAccountService;
        this.agencyAccountService = agencyAccountService;
    }

    public AccountService getAccountService(boolean isAgency) {
        if (isAgency)
            return agencyAccountService;
        else
            return userAccountService;
    }
}
