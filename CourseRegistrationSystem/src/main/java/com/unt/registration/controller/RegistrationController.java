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
import com.unt.registration.util.Grade;
import com.unt.registration.util.Payment;
import com.unt.registration.util.SelectCriteria;
import com.unt.registration.util.SwapCourse;
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
	public int enroll(@RequestBody EnrollObject enrollObject) {
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
	public int swap(@RequestBody SwapCourse swapCourse) {
		return registrationServiceImpl.swapCourse(swapCourse);
	}
	@PostMapping(path = "/postPayment", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public int postPayment(@RequestBody Payment payment) {
		return registrationServiceImpl.postPayment(payment);
	}

	@PostMapping(path = "/viewGrades", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Grade> viewGrades(@RequestBody User user) {
		return registrationDaoImpl.viewGrades(user);
	}

	@PostMapping(path = "/pastPayments", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Payment> pastPayments(@RequestBody User user) {
		return registrationDaoImpl.pastPayments(user);
	}
	@PostMapping(path = "/viewDues", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public float viewDues(@RequestBody Payment payment) {
		return registrationServiceImpl.viewDues(payment);
	}
	@PostMapping(path = "/mandatoryCoursesDone", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Course> mandatoryCoursesDone(@RequestBody User user) {
		return registrationDaoImpl.mandatoryCoursesDone(user);
	}
	@PostMapping(path = "/mandatoryCoursesNotDone", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Course> mandatoryCoursesNotDone(@RequestBody User user) {
		return registrationDaoImpl.mandatoryCoursesNotDone(user);
	}
	@PostMapping(path = "/fetchAvailableCourses", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Course> fetchAvailableCourses(@RequestBody SelectCriteria selectCriteria) {
		return registrationServiceImpl.fetchAvailableCourses(selectCriteria);
	}
	@PostMapping(path = "/fetchNotEnrolledCourses", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Course> fetchNotEnrolledCourses(@RequestBody User user) {
		return registrationServiceImpl.fetchNotEnrolledCourses(user);
	}
	@PostMapping(path = "/addCourse", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public int addCourse(@RequestBody Course course) {
		return registrationDaoImpl.addCourse(course);
	}
	@GetMapping(path = "/fetchExistingCourses", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Course> fetchExistingCourses() {
		return registrationServiceImpl.fetchExistingCourses();
	}
	@PostMapping(path = "/deleteCourse", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public int deleteCourse(@RequestBody String courseId) {
		return registrationDaoImpl.deleteCourse(courseId);
	}

}