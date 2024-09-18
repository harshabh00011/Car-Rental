package com.harshabh.services.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.harshabh.dto.SignupRequest;
import com.harshabh.dto.UserDto;
import com.harshabh.entity.User;
import com.harshabh.enums.UserRole;
import com.harshabh.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void createAdminAccount() {
		User adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
		if(adminAccount == null) {
			User newAdminAccount = new User();
			newAdminAccount.setName("Admin");
			newAdminAccount.setEmail("admin@test.com");
			newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));
			newAdminAccount.setUserRole(UserRole.ADMIN);
			userRepository.save(newAdminAccount);
			System.out.println("Admin account created successfully");
		}
	}

	@Override
	public UserDto createCustomer(SignupRequest signupRequest) {
		User user = new User();
		user.setName(signupRequest.getName());
		user.setEmail(signupRequest.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		user.setUserRole(UserRole.CUSTOMER);
		
		User createUser = userRepository.save(user);
		UserDto userDto = new UserDto();
		
		userDto.setId(createUser.getId());
		
		return userDto;
	}

	@Override
	public boolean hasCustomerWithEmail(String email) {
		return userRepository.findFirstByEmail(email).isPresent();
	}
	
}
