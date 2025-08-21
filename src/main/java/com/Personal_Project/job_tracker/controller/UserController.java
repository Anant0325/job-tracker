package com.Personal_Project.job_tracker.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Personal_Project.job_tracker.model.Users;
import com.Personal_Project.job_tracker.repo.UserRepo;
import com.Personal_Project.job_tracker.service.JwtService;

@RestController
@RequestMapping("/auth")
public class UserController {
	
	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	public UserController(UserRepo userRepo, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}
	
	
	@PostMapping("/signup")
	public String signup(@RequestParam String email, @RequestParam String password) {
		if (userRepo.findByEmail(email) != null||email.isEmpty() || password.isEmpty()|| !email.contains("@")) {
			return "User id and password  already exists";
		}
		
		String encodedPassword = passwordEncoder.encode(password);
		Users newUser = new Users();
		newUser.setEmail(email);
		newUser.setPasswordHash(encodedPassword);
		newUser.setCreatedAt(java.time.LocalDateTime.now().toString()); // Set current time as createdAt
		
		userRepo.save(newUser);
		
		return "User registered successfully";
	}
	public record SignupRequest(String email, String password) {}
	
	public record MeResponse(String email) {}

	@GetMapping("/me")
	public MeResponse getCurrentUser(@AuthenticationPrincipal UserDetails user) {
	    return new MeResponse(user.getUsername());
	}
	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password) {
	    Users user = userRepo.findByEmail(email);
	    if (user == null) {
	        return "User not found";
	    }

	    if (passwordEncoder.matches(password, user.getPasswordHash())) {
	        // use the instance, not the class
	        String token = jwtService.generateToken(user.getEmail());
	        return "Bearer " + token;
	    } else {
	        return "Invalid credentials";
	    }
	}



	
	
	
	

}
