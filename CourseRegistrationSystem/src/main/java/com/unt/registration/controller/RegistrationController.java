package com.unt.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unt.registration.service.RegistrationServiceImpl;
import com.unt.registration.util.User;

@RestController
@RequestMapping("/RegistrationController")
public class RegistrationController {
	
	@Autowired
	RegistrationServiceImpl registrationServiceImpl;
	
	@GetMapping(path="/getUserDetails", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public User getUserDetails() {
	User user= registrationServiceImpl.getUserDetails();
	return user;
	
	
}
}