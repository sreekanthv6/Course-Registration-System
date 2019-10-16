package com.unt.registration.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unt.registration.util.Department;
import com.unt.registration.util.SelectCriteria;
import com.unt.registration.service.RegistrationServiceImpl;
import com.unt.registration.util.User;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/RegistrationController")
public class RegistrationController {
	
	@Autowired
	RegistrationServiceImpl registrationServiceImpl;
	
	@PostMapping(path = "/login", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public User userValidate(@RequestBody User user) {
		return registrationServiceImpl.userValidate(user.getId(), user.getPassword());
	}
	@PostMapping(path = "/signup", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public String signup(@RequestBody User user) {
		return registrationServiceImpl.signup(user);
	}
	
	@GetMapping(path="/fetchDeptNames", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Department> fetchAllDepartments(){
		return registrationServiceImpl.fetchAllDepartments();
	}
	
	@PostMapping(path="/resetPassword", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public String resetPassword(@RequestBody User user){
		return registrationServiceImpl.resetPassword(user);
	}
	@PostMapping(path="/getCourses", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public int resetPassword(@RequestBody SelectCriteria selectcriteria){
		System.out.println("Degree is "+selectcriteria.getDegree()+" and Department is "+selectcriteria.getDeptId());
		return 1;
	}
	
}