package com.exchange.demo.model;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class User implements UserDetails {

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	// the password is indher
	@Override
	public String getPassword() {
		return "$2a$10$YHNty.63aTrBLMDmd6XCuOHmFMbdWPDCnJWztChQKh9spoAMl3lrq";
	}

	@Override
	public String getUsername() {
		return "zahid";
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
