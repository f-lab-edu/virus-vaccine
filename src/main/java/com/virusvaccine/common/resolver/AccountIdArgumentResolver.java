package com.virusvaccine.common.resolver;

import com.virusvaccine.common.annotation.AccountId;
import com.virusvaccine.common.exception.UnauthorizedException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

import static com.virusvaccine.user.service.AccountService.SESSION_KEY_USER;

@Component
public class AccountIdArgumentResolver implements HandlerMethodArgumentResolver {
    private final HttpSession session;

    public AccountIdArgumentResolver(HttpSession session) {
        this.session = session;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(AccountId.class) != null;
    }

    @Override
    public Long resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object accountId = session.getAttribute(SESSION_KEY_USER);


        return (Long) accountId;
    }
}
