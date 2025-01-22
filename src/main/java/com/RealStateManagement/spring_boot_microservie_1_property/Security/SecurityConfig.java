package com.RealStateManagement.spring_boot_microservie_1_property.Security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Value("${service.security.secure-key-username}")
    private String SECURE_KEY_USERNAME;

    @Value("${service.security.secure-key-password}")
    private String SECURE_KEY_PASSWORD;

    @Value("${service.security.secure-key-username-2}")
    private String SECURE_KEY_USERNAME_2;

    @Value("${service.security.secure-key-password-2}")
    private String SECURE_KEY_PASSWORD_2;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Configure authorization rules
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().hasRole("ADMIN") // Ensure "ROLE_" prefix is used in role names
                )
                .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs or non-browser clients
                .httpBasic(Customizer.withDefaults()) // Enable basic authentication
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDeniedHandler()) // Handle access denial
                );

        return http.build();
    }

    @Bean
    public AccessDeniedHandler customAccessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(403); // Set HTTP status to 403 Forbidden
            response.setHeader("Location", "https://youtu.be/xvFZjo5PgG0"); // Redirect header
            response.flushBuffer(); // Complete response
        };
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        // Configure in-memory authentication
        authenticationManagerBuilder
                .inMemoryAuthentication()
                .withUser("SECURE_KEY_USERNAME")
                .password(passwordEncoder.encode("SECURE_KEY_PASSWORD"))
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"))
                .and()
                .withUser("SECURE_KEY_USERNAME_2")
                .password(passwordEncoder.encode("SECURE_KEY_PASSWORD_2"))
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_DEV"));

        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
