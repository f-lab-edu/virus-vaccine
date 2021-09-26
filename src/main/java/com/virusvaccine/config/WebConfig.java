package com.virusvaccine.config;

import com.virusvaccine.controller.interceptor.AuthInterceptor;
import com.virusvaccine.controller.resolver.AccountIdArgumentResolver;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@ComponentScan("com.virusvaccine.controller")
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    private final AccountIdArgumentResolver accountIdArgumentResolver;

    public WebConfig(AuthInterceptor authInterceptor, AccountIdArgumentResolver accountIdArgumentResolver) {
        this.authInterceptor = authInterceptor;
        this.accountIdArgumentResolver = accountIdArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(accountIdArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/**");
    }
}
