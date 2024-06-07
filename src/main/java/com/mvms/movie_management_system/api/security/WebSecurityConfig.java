package com.mvms.movie_management_system.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .cors(cors -> cors.disable()) // Disable CORS
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() // Allow all requests
                );

        return http.build();
    }
}
