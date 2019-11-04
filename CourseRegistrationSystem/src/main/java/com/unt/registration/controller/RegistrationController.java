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

import com.unt.registration.util.Course;
import com.unt.registration.util.Department;
import com.unt.registration.util.EnrollObject;
import com.unt.registration.util.Enrollment;
import com.unt.registration.util.Payment;
import com.unt.registration.util.SelectCriteria;
import com.unt.registration.dao.RegistrationDaoImpl;
import com.unt.registration.service.RegistrationServiceImpl;
import com.unt.registration.util.User;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/RegistrationController")
public class RegistrationController {

	@Autowired
	RegistrationServiceImpl registrationServiceImpl;
	@Autowired
	RegistrationDaoImpl registrationDaoImpl;

	@PostMapping(path = "/login", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public User userValidate(@RequestBody User user) {
		return registrationServiceImpl.userValidate(user.getId(), user.getPassword());
	}

	@PostMapping(path = "/signup", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public String signup(@RequestBody User user) {
		return registrationServiceImpl.signup(user);
	}

	@GetMapping(path = "/fetchDeptNames", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Department> fetchAllDepartments() {
		return registrationServiceImpl.fetchAllDepartments();
	}

	@PostMapping(path = "/resetPassword", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public String resetPassword(@RequestBody User user) {
		return registrationServiceImpl.resetPassword(user);
	}

	@PostMapping(path = "/getCourses", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Course> getCourses(@RequestBody SelectCriteria selectcriteria) {
		return registrationServiceImpl.getCourses(selectcriteria);
	}

	@PostMapping(path = "/findCourse", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Course findCourse(@RequestBody String courseId) {
		return registrationServiceImpl.findCourse(courseId);
	}

	@PostMapping(path = "/enroll", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public String enroll(@RequestBody EnrollObject enrollObject) {
		return registrationServiceImpl.enroll(enrollObject);
	}

	@PostMapping(path = "/dropCourse", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public boolean drop(@RequestBody EnrollObject enrollObject) {
		return registrationServiceImpl.dropCourse(enrollObject);
	}

	@PostMapping(path = "/fetchEnrolledCourses", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Course> fetchEnrolledCourses(@RequestBody User user) {
		return registrationServiceImpl.fetchEnrolledCourses(user);
	}
	@PostMapping(path = "/swap", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Course> swap(@RequestBody User user) {
		return registrationServiceImpl.fetchEnrolledCourses(user);
	}
//	@PostMapping(path = "/viewClasses", produces = { MediaType.APPLICATION_JSON_VALUE,
//			MediaType.APPLICATION_XML_VALUE })
//	public List<Enrollment> viewClasses(@RequestBody User user) {
//		return registrationDaoImpl.getClasses(user.getId());
//	}

	@PostMapping(path = "/viewGrades", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Enrollment> viewGrades(@RequestBody User user) {
		return registrationDaoImpl.viewGrades(user);
	}

	@PostMapping(path = "/pastPayment", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Payment> pastPayments(@RequestBody User user) {
		return registrationDaoImpl.pastPayments(user);
	}

}