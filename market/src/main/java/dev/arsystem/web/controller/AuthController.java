package dev.arsystem.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.arsystem.domain.dto.AuthenticationRequest;
import dev.arsystem.domain.dto.AuthenticationResponse;
import dev.arsystem.web.security.JWTUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	public AuthController(AuthenticationManager authManager, JWTUtil jwtUtil) {
		this.authManager = authManager;
		this.jwtUtil = jwtUtil;
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request) {
		try {
			Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
			
			UserDetails user = (UserDetails) auth.getPrincipal();
			String token = jwtUtil.generateToken(user);
			
			return ResponseEntity.ok(new AuthenticationResponse(token));
		} catch (BadCredentialsException e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	
	private AuthenticationManager authManager;
	private JWTUtil jwtUtil;
}
