package dev.arsystem.web.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import dev.arsystem.web.security.JWTUtil;
import io.jsonwebtoken.Claims;

@Component
public class JWTFilterRequest extends OncePerRequestFilter {
	
	public JWTFilterRequest(JWTUtil jwtUtil, UserDetailsService service) {
		this.jwtUtil = jwtUtil;
		this.service = service;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request
			, HttpServletResponse response
			, FilterChain filterChain)
			throws ServletException, IOException {
		SecurityContextHolder.getContext().setAuthentication(authentication(request));
		filterChain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken authentication(HttpServletRequest request) {
		
		String token = getToken(request);
		
		if (token == null)
			return null;
		
		Claims claims = jwtUtil.getClaims(token);
		String userName = jwtUtil.extractUserName(claims);
		
		if (claims == null || claims.isEmpty() || userName == null)
			return null;
		
		UserDetails details = service.loadUserByUsername(userName);
		
		if (details == null)
			return null;
		
		if (!jwtUtil.validateToken(claims, details))
			return null;
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(details.getUsername(), null, details.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
		return authentication;
	}
	
	private String getToken(HttpServletRequest request) {
		String token = request.getHeader(AUTHORIZATION);
		
		if (token != null && token.startsWith(BEARER))
			return token.replaceAll(BEARER, "");
		
		return null;
	}
	
	private JWTUtil jwtUtil;
	private UserDetailsService service;
	
	private static final String BEARER = "Bearer";
	private static final String AUTHORIZATION = "Authorization";
}
