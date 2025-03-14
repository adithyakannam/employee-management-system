package com.adityainfotech.employee_management_system.util;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	
	
	private String message;
	private int statusCode;
	private T data;
	private LocalDateTime dateTime = LocalDateTime.now();
	
}
