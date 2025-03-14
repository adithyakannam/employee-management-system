package com.adityainfotech.employee_management_system.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.adityainfotech.employee_management_system.dto.Employee;
import com.adityainfotech.employee_management_system.service.EmployeeService;
import com.adityainfotech.employee_management_system.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*" , methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE,
		RequestMethod.PUT })
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<Employee>> register(@RequestBody Employee employee) {
		return employeeService.register(employee);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}

	@PostMapping("/find")
	public ResponseEntity<ResponseStructure<Employee>> findById(@RequestParam int id) {
		return employeeService.findById(id);
	}

	@PostMapping("/findAll")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	@PutMapping("/uploadimg")
	public ResponseEntity<ResponseStructure<Employee>> uploadImg(@RequestParam int id, @RequestParam MultipartFile file)
			throws IOException {
		return employeeService.uploadImg(id, file);
	}

	@PutMapping("/updateimg")
	public ResponseEntity<ResponseStructure<Employee>> updateImg(@RequestParam int id, @RequestBody MultipartFile file)
			throws IOException {
		return employeeService.updateImg(id, file);
	}

	@GetMapping("/getimg")
	public ResponseEntity<byte[]> getImg(@RequestParam int id) {
		return employeeService.getImg(id);
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<Employee>> login(@RequestBody Employee employee) {
		return employeeService.login(employee.getEmail(), employee.getPassword());
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<String>> deleteUser(@RequestParam int id) {
		return employeeService.deleteUser(id);
	}
}
