package com.car.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    SessionInterceptor sessionInterceptor() {
        return new SessionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.sessionInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/drv/login/**")
                .excludePathPatterns("/drv/bind/**")
                .excludePathPatterns("/callback/**")
                .excludePathPatterns("/web/**")
                .excludePathPatterns("/drv/**");
    }
}
