package com.adityainfotech.employee_management_system.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MyEmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(String to) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject("Sucessfull");
		message.setText("Your Account is created sucessfull");
		
		mailSender.send(message);
	}
}
