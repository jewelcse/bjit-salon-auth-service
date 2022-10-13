package com.bjit.salon.auth.service.config;

import com.bjit.salon.auth.service.security.jwt.CustomAuthTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public CustomAuthTokenFilter authTokenFilter(){
        return new CustomAuthTokenFilter();
    }
}
