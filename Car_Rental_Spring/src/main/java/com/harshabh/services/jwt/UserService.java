package com.harshabh.services.jwt;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
	
	public UserDetailsService userDetailsService();

}
