package com.cg.employee.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.employee.Entity.Employee;
import com.cg.employee.Repository.EmployeeDao;
import com.cg.employee.dto.EmployeeDto;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		
		
		Employee emp = new Employee();
		emp.setName(employeeDto.getName());
		emp.setEmail(employeeDto.getEmail());
		emp.setPassword(encoder.encode(employeeDto.getPassword()));
		
		Employee e = employeeDao.saveAndFlush(emp);
		EmployeeDto empDto = new EmployeeDto();
		empDto.setId(e.getId());
		empDto.setName(e.getName());
		empDto.setEmail(e.getEmail());
		return empDto;
		
		
		
	
	}
	

}
