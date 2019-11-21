package com.unt.registration.dao;

import java.util.List;

import com.unt.registration.util.Course;
import com.unt.registration.util.Department;
import com.unt.registration.util.EnrollObject;
import com.unt.registration.util.Enrollment;
import com.unt.registration.util.Grade;
import com.unt.registration.util.Payment;
import com.unt.registration.util.SelectCriteria;
import com.unt.registration.util.SwapCourse;
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
	public int enroll(EnrollObject enrollObject);
	public List<Course> fetchEnrolledCourses(User user);
	public int dropCourse(EnrollObject enrollObject);
	public int decreaseStrength(String id);
	public int increaseStrength(String id);
	public int checkPrerequisites(String userId, String courseId);
	public String getPrerequisites(String id);
	public int postPayment(Payment payment, String paymentId, String strDate);
	public List<Payment> pastPayments(User user);
	public List<Grade> viewGrades(User user);
	public float pastPaymentsAmount(Payment payment);
	public float totalAmount(Payment payment);
	public List<Course> mandatoryCoursesNotDone(User user);
	public List<Course> mandatoryCoursesDone(User user);
	public int ifPrerequisitesExist(String courseId);
	public List<Course> fetchAvailableCourses(SelectCriteria selectCriteria);
	public List<Course> fetchNotEnrolledCourses(User user);
	public int addCourse(Course course);
}
