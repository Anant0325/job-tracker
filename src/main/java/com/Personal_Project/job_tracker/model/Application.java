package com.Personal_Project.job_tracker.model;

import java.time.LocalDate;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//@Entity(name = "application")
@Entity
@Table(name = "applications")
public class Application {
	
	 public enum Status {
	        APPLIED,
	        NOT_APPLIED,
	        INTERVIEW,
	        OFFERED,
	        REJECTED
	    }
	 public enum Location {
	        ONSITE,
	        REMOTE,
	        HYBRID
	    }
	
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Jobid;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id", nullable = false)
	    private Users user;
	@Column(nullable = false)
	private String CompanyName;
	@Column(nullable = false)
	private String JobTitle;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Location location;
	@Column(nullable = false)
	private String JobDescription;
	@Column(nullable = false)
	private String JobLink;
	@Column(nullable = false)
	private LocalDate ApplicationDate;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	public Status status;
	@Column(nullable = false)
	private LocalDate LastFollowUp;
	@Column(nullable = false)
	private LocalDate createdAt;
	@Column(nullable = false)
	private LocalDate updatedAt;
	public Application() {
		
	}
		
	public Application(Long jobid, Users user, String companyName, String jobTitle, Location location,
			String jobDescription, String jobLink, LocalDate applicationDate, Status status, LocalDate lastFollowUp,
			LocalDate createdAt, LocalDate updatedAt) {
		super();
		Jobid = jobid;
		this.user = user;
		CompanyName = companyName;
		JobTitle = jobTitle;
		this.location = location;
		JobDescription = jobDescription;
		JobLink = jobLink;
		ApplicationDate = applicationDate;
		this.status = status;
		LastFollowUp = lastFollowUp;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getJobid() {
		return Jobid;
	}

	public void setJobid(Long jobid) {
		Jobid = jobid;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public String getJobTitle() {
		return JobTitle;
	}

	public void setJobTitle(String jobTitle) {
		JobTitle = jobTitle;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getJobDescription() {
		return JobDescription;
	}

	public void setJobDescription(String jobDescription) {
		JobDescription = jobDescription;
	}

	public String getJobLink() {
		return JobLink;
	}

	public void setJobLink(String jobLink) {
		JobLink = jobLink;
	}

	public LocalDate getApplicationDate() {
		return ApplicationDate;
	}

	public void setApplicationDate(LocalDate applicationDate) {
		ApplicationDate = applicationDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getLastFollowUp() {
		return LastFollowUp;
	}

	public void setLastFollowUp(LocalDate lastFollowUp) {
		LastFollowUp = lastFollowUp;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
	

}
