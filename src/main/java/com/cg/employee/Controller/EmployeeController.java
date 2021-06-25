package com.cg.employee.Controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.employee.RequestModel.EmployeeRequest;
import com.cg.employee.ResponseModel.EmployeeResponse;
import com.cg.employee.Service.EmployeeService;
import com.cg.employee.dto.EmployeeDto;


@RestController
public class EmployeeController {

	@Autowired
	EmployeeService empService;
	
	@GetMapping("/getInfo")
	public ResponseEntity<String> getInfo(){
		String s = "This is an employee";
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	
	@PostMapping(path="/createEmployee", consumes={MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody() EmployeeRequest employee ){
		
		
		//map request body to dto 
		ModelMapper mapper = new ModelMapper();
		EmployeeDto employeeDto = mapper.map(employee,EmployeeDto.class);
		
		EmployeeDto afterService = empService.createEmployee(employeeDto);
		
		EmployeeResponse empResponse = new EmployeeResponse();
		empResponse.setId(afterService.getId());
		empResponse.setName(afterService.getName());
		
		return new ResponseEntity<EmployeeResponse>(empResponse,HttpStatus.OK);
	}
	
	
	
}
