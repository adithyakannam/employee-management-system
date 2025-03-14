package com.adityainfotech.employee_management_system.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.adityainfotech.employee_management_system.myexception.IdNotFound;
import com.adityainfotech.employee_management_system.myexception.ImageTooLarge;
import com.adityainfotech.employee_management_system.util.ResponseStructure;

@RestControllerAdvice
public class MyExceptionHandler {
	ResponseStructure<String> structure = new ResponseStructure<String>();

	@ExceptionHandler(IdNotFound.class)
	public ResponseEntity<ResponseStructure<String>> idNotFound(IdNotFound idNotFound){
		structure.setData(idNotFound.getMessage());
		structure.setMessage("Error");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ImageTooLarge.class)
	public ResponseEntity<ResponseStructure<String>> imageTooLarge(ImageTooLarge imageTooLarge){
		structure.setData(imageTooLarge.getMessage());
		structure.setMessage("File size exceeds 2MB limit");
        structure.setStatusCode(HttpStatus.PAYLOAD_TOO_LARGE.value());  
        return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.PAYLOAD_TOO_LARGE);
	}
}
