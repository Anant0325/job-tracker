package com.Personal_Project.job_tracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity(name = "users")
public class Users {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String passwordHash;
	@Column(nullable = false)
	private String createdAt;
	
	public Users() {
		
	}
	
	public Users(Long user_id, String email, String passwordHash, String createdAt) {
		super();
		this.user_id = user_id;
		this.email = email;
		this.passwordHash = passwordHash;
		this.createdAt = createdAt;
	}
	
	public Long getId() {
		return user_id;
	}
	public void setId(Long user_id) {
		this.user_id = user_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
}
