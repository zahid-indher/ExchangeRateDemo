package com.exchange.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
//This class takes care of security of the API.
@Configuration
public class WebSecurityConfig {

//    @Value("${spring.security.user.name}")
//    private String username;
//
//    @Value("${spring.security.user.password}")
//    private String password;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/calculate").permitAll() // Adjust as needed
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()); // Use the updated method
        return http.build();
    }

//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = 
//            http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder
//            .inMemoryAuthentication()
//            .withUser(username) // Use username from properties
//            .password("{noop}" + password) // Use password from properties, with noop for plain text
//            .roles("USER"); // Set role (optional)
//        return authenticationManagerBuilder.build();
//    }
}
