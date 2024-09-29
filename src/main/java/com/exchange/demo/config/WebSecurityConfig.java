package com.exchange.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

//This class takes care of security of the API.
@Configuration
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/calculate").permitAll() // Adjust as needed
				.anyRequest().authenticated()).httpBasic(Customizer.withDefaults()); // Use the updated method
		return http.build();
	}
}
