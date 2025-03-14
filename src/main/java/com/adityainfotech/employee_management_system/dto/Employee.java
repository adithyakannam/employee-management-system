package com.adityainfotech.employee_management_system.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eid;
	private String name;
	private double sal;
	private String address;
	@Column(unique = true, nullable = false)
	private String email;
	private String password;
	@Column(nullable = false)
	private long contact;
	@Lob // consider large object
	@Column(columnDefinition = "longblob", length = 999999999) // size
	private byte img[];
}
