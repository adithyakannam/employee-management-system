package com.adityainfotech.employee_management_system.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adityainfotech.employee_management_system.dto.Employee;
import com.adityainfotech.employee_management_system.repository.EmployeeRepository;

@Repository
public class EmployeeDao {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee register(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Optional<Employee> findById(int id) {
		return employeeRepository.findById(id);
	}
	
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
	public Employee findByEmail(String email) {
		return employeeRepository.findByEmail(email);
	}
	
	public void deleteUser(int id) {
		 employeeRepository.deleteById(id);
	}
	
	
	
	

	
}
