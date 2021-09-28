package com.virusvaccine.common.config;

import com.virusvaccine.common.interceptor.AuthInterceptor;
import com.virusvaccine.common.resolver.AccountIdArgumentResolver;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@ComponentScan("com.virusvaccine")
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
