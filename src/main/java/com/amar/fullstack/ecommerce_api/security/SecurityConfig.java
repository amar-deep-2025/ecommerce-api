package com.amar.fullstack.ecommerce_api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/users/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/users/**").hasRole("ADMIN")
                                .requestMatchers("/api/products/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"api/products/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"api/products/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"api/products/**").hasRole("ADMIN")
                                .requestMatchers("/api/cart/**").hasRole("USER")
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
