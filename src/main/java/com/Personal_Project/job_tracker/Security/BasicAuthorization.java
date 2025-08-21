package com.Personal_Project.job_tracker.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class BasicAuthorization {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	 @Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
	    return cfg.getAuthenticationManager();
	  }
	
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http,JwtAuthFilter jwtAuthFilter) throws Exception {
		
		http.authorizeHttpRequests(auth -> auth
			    .requestMatchers("/h2-console/**","/auth/signup","/auth/login").permitAll()// Match all API endpoints
				.anyRequest().authenticated() // Require authentication for all requests
			);
			

		http.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Use stateless sessions
		http.httpBasic(withDefaults());

		http.csrf(csrf -> csrf.disable());

		//http.csrf(AbstractHttpConfigurer::disable);

		http.headers(headers -> headers.frameOptions(frameOptionsConfig-> frameOptionsConfig.disable()));

		// http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
			
	
	}
	
}
