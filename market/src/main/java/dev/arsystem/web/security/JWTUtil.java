package dev.arsystem.web.security;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	public String generateToken(UserDetails userDetail) {
		return Jwts.builder()
				.setSubject(userDetail.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, KEY)
				.compact();
	}
	
	public boolean validateToken(Claims claims, UserDetails userDetails) {
		return userDetails.getUsername().equals(claims.getSubject())
				&& !isTokenExpired(claims);
	}
	
	public boolean validateToken(String token, UserDetails userDetails) {
		return validateToken(getClaims(token), userDetails);
	}
	
	public boolean isTokenExpired(String token) {
		return isTokenExpired(getClaims(token));
	}
	
	public boolean isTokenExpired(Claims claims) {
		return claims == null || claims.isEmpty() || claims.getExpiration().before(new Date());
	}
	
	public Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(KEY)
				.parseClaimsJws(token)
				.getBody();
	}
	
	public String extractUserName(Claims claims) {
		return claims != null ? claims.getSubject() : null;
	}
	
	public String extractUserName(String token) {
		return extractUserName(getClaims(token));
	}
	
	private static final String KEY = "PL4TZ1";
}
