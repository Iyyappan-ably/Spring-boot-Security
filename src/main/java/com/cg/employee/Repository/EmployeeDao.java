package com.cg.employee.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.employee.Entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,Integer> {

	Employee findByEmail(String email);
}
