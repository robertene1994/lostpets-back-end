package com.robert.lostpets.config.security.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
 * Clase que contiene el método de la autenticación (token) de los usuarios así
 * como la comprobación de que todas las peticiones entrantes están autorizadas.
 * 
 * @author Robert Ene
 */
@Component
@SuppressWarnings("static-access")
public class TokenAuthenticationService {

	private static String authHeader;

	private static String tokenPrefix;

	private static Long expireHours;

	private static String plainSecret;

	private static String encodedSecret;

	@Value("${jwt.auth.header}")
	public void setAuthHeader(String authHeader) {
		this.authHeader = authHeader;
	}
	
	public static String getAuthHeader() {
		return authHeader;
	}

	@Value("${jwt.token.prefix}")
	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}

	@Value("${jwt.expire.hours}")
	public void setExpireHours(Long expireHours) {
		this.expireHours = expireHours;
	}

	@Value("${jwt.token.secret}")
	public void setPlainSecret(String plainSecret) {
		this.plainSecret = plainSecret;
	}

	@PostConstruct
	void init() {
		if (StringUtils.isEmpty(plainSecret)) {
			throw new IllegalArgumentException("JWT secret cannot be null or empty.");
		} else {
			encodedSecret = Base64.getEncoder()
					.encodeToString(TokenAuthenticationService.plainSecret.getBytes());
		}
	}

	public static void addAuthentication(HttpServletResponse res,
			String userName, String role) throws IOException {
		String jwt = Jwts.builder()
				.setSubject(userName)
				.setExpiration(getExpirationTime())
				.claim("role", role)
				.signWith(SignatureAlgorithm.HS256, encodedSecret)
				.compact();
		res.addHeader(authHeader, tokenPrefix + " " + jwt);
		res.getWriter().write("");
		res.getWriter().flush();
		res.getWriter().close();
	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		return getAuthentication(request.getHeader(authHeader));
	}
	
	public static Authentication getAuthentication(String token) {
		if (token != null && !token.trim().isEmpty()) {
			Claims claims = null;
			
			try {
				claims = Jwts.parser()
						.setSigningKey(encodedSecret)
						.parseClaimsJws(token.replace(tokenPrefix, ""))
						.getBody();
			} catch (SignatureException e) {
				return null;
			}
			
			String userName = claims.getSubject();
			String role = (String) claims.get("role");

			Collection<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new GrantedAuthority() {
				private static final long serialVersionUID = 1L;

				@Override
				public String getAuthority() {
					return role;
				}
			});

			UsernamePasswordAuthenticationToken authentication
				= new UsernamePasswordAuthenticationToken(userName, null, authorities);
			return (userName != null && role != null) ? authentication : null;
		}
		return null;
	}

	private static Date getExpirationTime() {
		return new Date(TimeUnit.HOURS.toMillis(expireHours) + new Date().getTime());
	}
}
