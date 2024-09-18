package com.harshabh.services.auth;

import com.harshabh.dto.SignupRequest;
import com.harshabh.dto.UserDto;

public interface AuthService {
	
	UserDto createCustomer(SignupRequest signupRequest);
	
	boolean hasCustomerWithEmail(String email);

}
