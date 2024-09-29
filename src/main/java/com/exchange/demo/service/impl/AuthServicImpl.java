package com.exchange.demo.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exchange.demo.model.User;

@Service
public class AuthServicImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = new User();
		if (!username.equals(user.getUsername())) {
			throw new UsernameNotFoundException("User name not found: " + username);
		}

		return user;
	}
}
