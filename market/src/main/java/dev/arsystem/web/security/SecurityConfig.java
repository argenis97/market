package dev.arsystem.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import dev.arsystem.web.security.filter.JWTFilterRequest;

/**
 * 
 * @author Argenis Rodríguez
 *
 */
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	AuthenticationManager authManager(HttpSecurity http
			, UserDetailsService service) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
			.userDetailsService(service)
			.and()
			.build();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, JWTFilterRequest filterRequest) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> 
						auth.antMatchers("/**/authenticate")
						.permitAll()
						.anyRequest()
						.authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(filterRequest, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui",
                "/swagger-resources/**", "/configuration/security",
                "/swagger-ui.html", "/webjars/**");
	}
}
