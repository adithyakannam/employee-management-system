package com.adityainfotech.employee_management_system.myexception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdNotFound extends RuntimeException {

	String message = "Id Not Found";

}
