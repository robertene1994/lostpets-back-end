package com.robert.lostpets.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.robert.lostpets.business.user.UserService;
import com.robert.lostpets.config.security.jwt.JWTAuthenticationFilter;
import com.robert.lostpets.config.security.jwt.JWTLoginFilter;
import com.robert.lostpets.entity.types.Role;

/**
 * Clase que define la capa de seguridad de la aplicación así como el acceso de
 * los diferentes roles a los recursos que les corresponden.
 * 
 * @author Robert Ene
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.cors()
			.and()
			.authorizeRequests()
			.antMatchers("/console/**", "/static/**", "/lostpets-ws/**").permitAll()
	        .antMatchers(HttpMethod.GET, "/user/uniqueEmail/**", "/resources/**").permitAll()
	        .antMatchers(HttpMethod.POST, "/user/**").permitAll()
	        .anyRequest().authenticated()
	        .antMatchers("/ad/**", "/chat/**", "/message/**").hasAuthority(Role.USER.toString())
	        .anyRequest().authenticated()
	        .and()
	        .logout()
	        .permitAll()
	        .and()
	        .addFilterBefore(new JWTLoginFilter("/user/logIn", authenticationManager()),
	        		UsernamePasswordAuthenticationFilter.class)
	        .addFilterBefore(new JWTAuthenticationFilter(), 
	        		UsernamePasswordAuthenticationFilter.class)
	        .headers()
			.frameOptions().sameOrigin()
			.httpStrictTransportSecurity().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userService);
	    authProvider.setPasswordEncoder(passwordEncoder);
	    auth.authenticationProvider(authProvider);
	}
}
