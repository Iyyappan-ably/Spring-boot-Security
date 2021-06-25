package com.cg.employee.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Autowired
	ObjectMapper mapper;
	
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		LoginModel model = null;
		try {
		model= mapper.readValue(request.getInputStream(),LoginModel.class);
        }
        catch(Exception e) {
        	
        }
		String username = model.getUsername();
		String password = model.getPassword();
		
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        
		
		Authentication auth = this.getAuthenticationManager().authenticate(authRequest);
		
		return auth;
		
	}
	
	

}
