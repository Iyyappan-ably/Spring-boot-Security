package com.cg.employee.ResponseModel;

public class EmployeeResponse {
	
	private int id;
	private String name;
	public EmployeeResponse() {
		
	}
	public EmployeeResponse(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
