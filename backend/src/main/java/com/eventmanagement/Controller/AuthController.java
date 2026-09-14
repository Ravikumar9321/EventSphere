package com.eventmanagement.Controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.eventmanagement.Dto.*;
import com.eventmanagement.Entity.UserInfo;
import com.eventmanagement.Repository.UserRepository;
import com.eventmanagement.Service.UserService;
import com.eventmanagement.Utility.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auth")
@Tag(name = "Authentication",description = "User authentication API")
public class AuthController {
	@Autowired
	private UserService service;
	@Autowired
	private UserRepository repository;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private PasswordEncoder passwordEncoder;
	// i) Register User
	@Operation(summary = "Register user", description = "Creates a new user account")
	@ApiResponse(responseCode = "201", description = "User registered successfully")
	@ApiResponse(responseCode = "409", description = "User already exists")
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> registerUser( @Valid @RequestBody AuthRequest request) {
	    if (repository.findByEmail(request.email()).isPresent()) {
	        return new ResponseEntity<>(new AuthResponse("User already exists", null), HttpStatus.CONFLICT);
	    }
            
	    service.createuser(UserInfo.builder()
	            .email(request.email())
	            .password(passwordEncoder.encode(request.password()))
	            .build());

	    return new ResponseEntity<>(new AuthResponse("User Registered successfully", null), HttpStatus.CREATED);
	}

	// ii) Login User
	@Operation(summary = "Login user", description = "Authenticates user and returns JWT token")
	@ApiResponse(responseCode = "200", description = "Login successful")
	@ApiResponse(responseCode = "404", description = "User not registered")
	@ApiResponse(responseCode = "401", description = "Invalid password")
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> loginUser( @Valid @RequestBody AuthRequest request) {
		if(request.email()==null||request.password()==null)
	        return new ResponseEntity<>(new AuthResponse("Please enter email&password", null), HttpStatus.BAD_REQUEST);
	    Optional<UserInfo> user = repository.findByEmail(request.email());
	    if (user.isEmpty()) {
	        return new ResponseEntity<>(new AuthResponse("User not registered", null), HttpStatus.NOT_FOUND);
	    }
           
	    UserInfo userInfo = user.get();
	    if (!passwordEncoder.matches(request.password(), userInfo.getPassword())) {
	        return new ResponseEntity<>(new AuthResponse("Invalid password", null), HttpStatus.UNAUTHORIZED);
	    }

	    String token = jwtUtil.generateToken(request.email());
	    return ResponseEntity.ok(new AuthResponse("Login successful", token));
	}

	

}
