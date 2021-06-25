package com.cg.employee.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.employee.Entity.Employee;
import com.cg.employee.Repository.EmployeeDao;

@Service
public class EmployeeUserDetails implements UserDetailsService{

	@Autowired
	EmployeeDao empDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	  Employee emp = empDao.findByEmail(username);
	  if(emp == null) {
		  throw new UsernameNotFoundException("username is not found"); 
	  }
	  List<GrantedAuthority> list = new ArrayList<>();
	  UserDetails userDetails = new User(emp.getEmail(),emp.getPassword(),true,true,true,true,list);
	  return userDetails;
		
	}

	
	
}
