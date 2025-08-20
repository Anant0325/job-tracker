package com.Personal_Project.job_tracker.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Personal_Project.job_tracker.model.Users;
public interface UserRepo extends JpaRepository<Users, Long> {
	
	// Custom query to find a user by email
	Users findByEmail(String email);
	
	// Custom query to check if a user exists by email
	
	
	// Additional methods can be defined here as needed

}
