package com.basmaonline.ws.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.basmaonline.ws.SpringApplicationContext;
import com.basmaonline.ws.request.UserLoginRequest;
import com.basmaonline.ws.services.UserService;
import com.basmaonline.ws.shared.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

		
		private final AuthenticationManager authenticationManager;

		public AuthenticationFilter(AuthenticationManager authenticationManager) {
			this.authenticationManager = authenticationManager;
		}
		
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,HttpServletResponse res)  {
		try {
			
			UserLoginRequest creds = new ObjectMapper().readValue(req.getInputStream(), UserLoginRequest.class);
			
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req,HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
		
		String userName = ((User) auth.getPrincipal()).getUsername();
		String token = Jwts.builder()
				.setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET)
				.compact();
		UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
		UserDto userDto =	userService.getUser(userName); //Recup Email Utilisateur
			
		res.addHeader(SecurityConstants.HEADER_STRING,SecurityConstants.TOKEN_PREFIX + token);
		
		res.addHeader("user id", userDto.getUserId());
	}
	
}
