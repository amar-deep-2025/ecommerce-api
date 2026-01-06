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
                                .formLogin(form -> form.disable())
                                .httpBasic(basic -> basic.disable())
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(
                                                                "/swagger-ui.html",
                                                                "/swagger-ui/**",
                                                                "/v3/api-docs/**")
                                                .permitAll()

                                                .requestMatchers("/api/auth/**").permitAll()

                                                // 👤 USER APIs (USER role)
                                                .requestMatchers("/api/cart/**").hasAnyRole("USER", "ADMIN")

                                                // 👥 USER MANAGEMENT (ADMIN only)
                                                .requestMatchers("/api/users/**").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/api/users/**").hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")

                                                // 📦 PRODUCTS
                                                .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/api/products/**").hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.PUT, "/api/products/**").hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("ADMIN")
                                                // 🛒 ORDERS
                                                .requestMatchers(HttpMethod.GET, "api/orders/**").permitAll()
                                                .requestMatchers(HttpMethod.POST, "api/orders/**")
                                                .hasAnyRole("USER", "ADMIN")
                                                .requestMatchers(HttpMethod.POST, "api/payments/**")
                                                .hasAnyRole("USER", "ADMIN")

                                                // 🔒 Everything else
                                                .anyRequest().authenticated())

                                // ✅ JWT Filter
                                .addFilterBefore(jwtAuthenticationFilter,
                                                UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}
