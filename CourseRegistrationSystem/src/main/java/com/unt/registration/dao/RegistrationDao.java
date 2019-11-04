package com.unt.registration.dao;

import java.util.List;

import com.unt.registration.util.Course;
import com.unt.registration.util.Department;
import com.unt.registration.util.EnrollObject;
import com.unt.registration.util.Enrollment;
import com.unt.registration.util.Grade;
import com.unt.registration.util.Payment;
import com.unt.registration.util.SelectCriteria;
import com.unt.registration.util.User;

public interface RegistrationDao {

	public User userValidate(String id);
	public int userExists(String id);
	public int userIdProvided(String id);
	public Boolean signup(User user);
	public List<Department> fetchAllDepartments();
	public Boolean resetPassword(User user);
	public String getEmail(String id);
	public List<Course> getCourses(SelectCriteria selectCriteria);
	public Course findCourse(String courseId);
	public String enroll(EnrollObject enrollObject);
	public List<Course> fetchEnrolledCourses(User user);
	public boolean dropCourse(EnrollObject enrollObject);
	public boolean decreaseStrength(String id);
	public boolean increaseStrength(String id);
	public int checkPrerequisites(String userId, String courseId);
	public String getPrerequisites(String id);
	public boolean postPayment(Payment payment);
	public List<Payment> pastPayments(User user);
	public List<Grade> viewGrades(User user);
}
