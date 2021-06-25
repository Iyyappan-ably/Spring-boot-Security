package com.cg.employee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	EmployeeUserDetails empDetails;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		http
		.authorizeRequests().antMatchers("/createEmployee","/h2-console").permitAll()
	    .and()
	    .authorizeRequests().antMatchers("/getInfo").authenticated()
	    .and()
		.authorizeRequests().anyRequest().permitAll()
		.and()
		.addFilter(getFilter());
		
	}
	
	
	@Bean
	EmployeeAuthenticationFilter getFilter() throws Exception {
		EmployeeAuthenticationFilter filter = new EmployeeAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManager());
		return filter;
	}

	@Bean
	DaoAuthenticationProvider dao() {
		DaoAuthenticationProvider da = new DaoAuthenticationProvider();
		da.setUserDetailsService(empDetails);
		da.setPasswordEncoder(encoder);
		return da;
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	  
		auth.authenticationProvider(dao());
	}
	
	
}