package com.harshabh.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshabh.dto.AuthenticationRequest;
import com.harshabh.dto.AuthenticationResponse;
import com.harshabh.dto.SignupRequest;
import com.harshabh.dto.UserDto;
import com.harshabh.entity.User;
import com.harshabh.repository.UserRepository;
import com.harshabh.services.auth.AuthService;
import com.harshabh.services.jwt.UserService;
import com.harshabh.utils.JWTUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUpCustomer(@RequestBody SignupRequest signupRequest)
	{
		if(authService.hasCustomerWithEmail(signupRequest.getEmail()))
		{
			return new ResponseEntity<>("Customer already exist with this email",HttpStatus.NOT_ACCEPTABLE);
		}
		UserDto createdCustomerDto = authService.createCustomer(signupRequest);
		if(createdCustomerDto == null)
		{
			return new ResponseEntity<>("Customer not created, Come again later", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws
		BadCredentialsException,
		DisabledException,
		UsernameNotFoundException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getEmail(), 
					authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password");
		}
		UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
		Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
		String jwt = jwtUtil.generateToken(userDetails);
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		if(optionalUser.isPresent()) {
			authenticationResponse.setJwt(jwt);
			authenticationResponse.setUserId(optionalUser.get().getId());
			authenticationResponse.setUserRole(optionalUser.get().getUserRole());
		}
		return authenticationResponse;
	}
	
}
