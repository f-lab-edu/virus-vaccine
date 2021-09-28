package com.virusvaccine.common.annotation;

import com.virusvaccine.user.service.AccountService.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Authorized {
    Role value();
}
