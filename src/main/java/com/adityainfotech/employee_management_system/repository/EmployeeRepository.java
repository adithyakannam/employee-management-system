package com.adityainfotech.employee_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adityainfotech.employee_management_system.dto.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	public Employee findByEmail(String email);
}