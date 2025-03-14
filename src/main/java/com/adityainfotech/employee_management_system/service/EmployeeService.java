package com.adityainfotech.employee_management_system.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.adityainfotech.employee_management_system.dao.EmployeeDao;
import com.adityainfotech.employee_management_system.dto.Employee;
import com.adityainfotech.employee_management_system.myexception.IdNotFound;
import com.adityainfotech.employee_management_system.myexception.ImageTooLarge;
import com.adityainfotech.employee_management_system.util.MyEmailService;
import com.adityainfotech.employee_management_system.util.ResponseStructure;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private MyEmailService emailService;

	private ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

	public ResponseEntity<ResponseStructure<Employee>> register(Employee employee) {
		Employee user = employeeDao.findByEmail(employee.getEmail());
		if (user != null) {
			throw new IdNotFound("User already exists");
		}
		Employee employeedb = employeeDao.register(employee);
		if (employeedb != null) {
			structure.setMessage("Data Stored");
			structure.setStatusCode(HttpStatus.CREATED.value());
			structure.setData(employeedb);
//		emailService.sendEmail(employeedb.getEmail());
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.CREATED);
		}

		return null;
	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(Employee employee) {
		Optional<Employee> employeedb = employeeDao.findById(employee.getEid());
		if (employeedb.isPresent()) {
			employee.setImg(employeedb.get().getImg());
			structure.setMessage("Details Updated");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(employeeDao.updateEmployee(employee));
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		}
		throw new IdNotFound();
	}

	public ResponseEntity<ResponseStructure<Employee>> findById(int id) {
		Optional<Employee> employeedb = employeeDao.findById(id);
		if (employeedb.isPresent()) {
			structure.setMessage("Details found");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(employeedb.get());
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		}
		throw new IdNotFound("Id not found");
	}

	
	public List<Employee> findAll() {
		return employeeDao.findAll();
	}
	
	
	public ResponseEntity<ResponseStructure<Employee>> uploadImg(int id, MultipartFile file) throws IOException {
		// Define a maximum file size limit (e.g., 2MB)
		final long MAX_FILE_SIZE = 2688110;// 2 MB in bytes
		// Check if the file size exceeds the maximum allowed size
		if (file.getSize() > MAX_FILE_SIZE) {
			System.out.println("image exception");
			throw new ImageTooLarge();
		}
		Optional<Employee> emdb = employeeDao.findById(id);
		if (emdb.isPresent()) {
			Employee employee = emdb.get();
			employee.setEid(id);
			employee.setImg(file.getBytes());
			structure.setMessage("Image uploded");
			structure.setData(employeeDao.updateEmployee(employee));
			structure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
		} else {
			throw new IdNotFound();
		}
	}

	public ResponseEntity<byte[]> getImg(int id) {
		Employee em = employeeDao.findById(id).get();
		if (em == null) {
			throw new IdNotFound();
		}
		byte[] employeedb = em.getImg();
		if (employeedb != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			return new ResponseEntity<byte[]>(employeedb, headers, HttpStatus.FOUND);
		}
		throw new IdNotFound();
	}
	
	

	public ResponseEntity<ResponseStructure<Employee>> updateImg(int id, MultipartFile file) throws IOException {
		final long MAX_FILE_SIZE = 2688110; // 2 MB in bytes
		System.out.println(file.getSize());
		if (file.getSize() > MAX_FILE_SIZE) {
			System.out.println("hello");
			throw new ImageTooLarge();
		}
		Employee employee = employeeDao.findById(id).get();
		if (employee != null) {
			byte[] employeedb = employee.getImg();
			if (employeedb != null) {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.IMAGE_PNG);
			}
			employee.setEid(id);
			employee.setImg(file.getBytes());
			structure.setMessage("Image uploded");
			structure.setData(employeeDao.updateEmployee(employee));
			structure.setStatusCode(HttpStatus.FOUND.value());

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
		} else {
			throw new IdNotFound();
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> login(String email, String password) {

		Employee employee = employeeDao.findByEmail(email);
		if (employee != null) {

			if (employee.getPassword().equals(password)) {
				structure.setData(employee);
				structure.setMessage("Login sucessfull");
				structure.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
			} else {
				throw new IdNotFound("password incorrect");
			}
		}
		throw new IdNotFound("email not found");
	}

	public ResponseEntity<ResponseStructure<String>> deleteUser(int id) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		Optional<Employee> employee = employeeDao.findById(id);
		if (employee.isPresent()) {
			employeeDao.deleteUser(id);
			structure.setMessage("Deleted sucessfull");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFound("User details not deleted");
		}
	}

}
