package com.unt.registration.service;

import java.util.List;

import com.unt.registration.util.Course;
import com.unt.registration.util.Department;
import com.unt.registration.util.EnrollObject;
import com.unt.registration.util.Payment;
import com.unt.registration.util.SelectCriteria;
import com.unt.registration.util.SwapCourse;
import com.unt.registration.util.User;

public interface RegistrationService {
	public User userValidate(String id, String password);
	public String signup(User user);
	public List<Department> fetchAllDepartments();
	public String resetPassword(User user);
	public List<Course> getCourses(SelectCriteria selectCriteria);
	public Course findCourse(String courseId);
	public int enroll(EnrollObject enrollObject);
	public List<Course> fetchEnrolledCourses(User user);
	public boolean dropCourse(EnrollObject enrollObject);
	public boolean sendEmail(EnrollObject enrollObject, int value);
	public float viewDues(Payment payment);
	public void sendEmailForPayment(Payment payment);
	public int postPayment(Payment payment);
	public int swapCourse(SwapCourse swapCourse);
	public List<Course> fetchAvailableCourses(SelectCriteria selectCriteria);
	public List<Course> fetchNotEnrolledCourses(User user);
	public List<Course> fetchExistingCourses();
	
}
