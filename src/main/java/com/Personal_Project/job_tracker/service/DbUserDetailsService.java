package com.Personal_Project.job_tracker.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Personal_Project.job_tracker.model.Users;
import com.Personal_Project.job_tracker.repo.UserRepo;

@Service
public class DbUserDetailsService implements UserDetailsService {
	
	
	private final UserRepo userRepo;
	public DbUserDetailsService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPasswordHash())   // <- BCrypt hash from DB
                .roles("USER")
                .build();
    }
	
}
