package com.unt.registration.service;

import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.unt.registration.dao.RegistrationDaoImpl;
import com.unt.registration.util.Course;
import com.unt.registration.util.Department;
import com.unt.registration.util.EnrollObject;
import com.unt.registration.util.Payment;
import com.unt.registration.util.SelectCriteria;
import com.unt.registration.util.SwapCourse;
import com.unt.registration.util.User;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	RegistrationDaoImpl registrationDaoImpl;
	@Autowired
	JavaMailSender javaMailSender;

	//Validate user
	@Override
	public User userValidate(String id, String password) {
		// TODO Auto-generated method stub
		User user = new User();
		if (registrationDaoImpl.userExists(id) == 0) {
			user.setId(id);
			return user;
		} else {
			user = registrationDaoImpl.userValidate(id);
			user.setUserExists(true);
			if (password.equals(user.getPassword()))
				user.setValidUser(true);
			return user;
		}
	}
	
	//User Signup
	@Override
	public String signup(User user) {
		if (registrationDaoImpl.userIdProvided(user.getId()) != 0) {
			if (registrationDaoImpl.userExists(user.getId()) == 0) {
				if (registrationDaoImpl.signup(user) == true)
					return "signed up";
				else
					return "Unexpected Error";
			} else
				return "Already signed up";
		} else
			return "Invalid user";
	}

	//fetch all departments
	@Override
	public List<Department> fetchAllDepartments() {
		// TODO Auto-generated method stub
		return registrationDaoImpl.fetchAllDepartments();
	}

	//reset password
	@Override
	public String resetPassword(User user) {
		// TODO Auto-generated method stub
		if (registrationDaoImpl.userExists(user.getId()) != 0) {
			if (user.getEmail().equals(registrationDaoImpl.getEmail(user.getId()))) {
				if (registrationDaoImpl.resetPassword(user) == true)
					return "Password reset";
				else
					return "Unexpected Error";
			} else
				return "invalid email";

		} else
			return "Invalid user ID";
	}

	//Get list of all courses
	@Override
	public List<Course> getCourses(SelectCriteria selectCriteria) {
		// TODO Auto-generated method stub
		return registrationDaoImpl.getCourses(selectCriteria);
	}

	//Find Course by courseid
	@Override
	public Course findCourse(String courseId) {
		// TODO Auto-generated method stub
		return registrationDaoImpl.findCourse(courseId);
	}

	//enroll
	@Override
	public int enroll(EnrollObject enrollObject) {
		// TODO Auto-generated method stub

		if (registrationDaoImpl.ifPrerequisitesExist(enrollObject.getCourseId()) == 0) {
			if (registrationDaoImpl.checkStrength(enrollObject.getCourseId()) > 0) {
				if (registrationDaoImpl.enroll(enrollObject) > 0
						&& registrationDaoImpl.increaseStrength(enrollObject.getCourseId()) > 0) {
					sendEmail(enrollObject, 1);
					return 1;
				}else
					return 0;
			} else
				return 2;
		} else {
			if (registrationDaoImpl.checkPrerequisites(enrollObject.getUserId(),
					registrationDaoImpl.getPrerequisites(enrollObject.getCourseId())) > 0) {
				if (registrationDaoImpl.checkStrength(enrollObject.getCourseId()) > 0) {
					if (registrationDaoImpl.enroll(enrollObject) > 0
							&& registrationDaoImpl.increaseStrength(enrollObject.getCourseId()) > 0)
						return 1;
					else
						return 0;
				} else
					return 2;
			} else
				return 4;
		}

	}
	
	//Fetch enrolled Courses
	@Override
	public List<Course> fetchEnrolledCourses(User user) {
		// TODO Auto-generated method stub
		return registrationDaoImpl.fetchEnrolledCourses(user);
	}
	
	//drop course
	@Override
	public boolean dropCourse(EnrollObject enrollObject) {
		// TODO Auto-generated method stub

		if (registrationDaoImpl.dropCourse(enrollObject) > 0
				&& registrationDaoImpl.decreaseStrength(enrollObject.getCourseId()) > 0) {
			sendEmail(enrollObject, 0);
			return true;
		}
		return false;

	}

	//send email for enrolling/swap/drop
	@Override
	public boolean sendEmail(EnrollObject enrollObject, int value) {
		String emailId;
		Course course,swap;

		try {

			MimeMessage msg = javaMailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			emailId = registrationDaoImpl.getEmail(enrollObject.getUserId());
			course = registrationDaoImpl.findCourse(enrollObject.getCourseId());
			swap=registrationDaoImpl.findCourse(enrollObject.getCourseId());
			

			helper.setTo(emailId);
			if (value == 0) {
				helper.setSubject("Notification for dropping");
				helper.setText("<h3>Dropped Course  " + course.getCourseName() + "Successfully!</h3>", true);
			} else if(value==1){
				helper.setSubject("Notification for Enrolling");
				helper.setText("<h3>Enrolled Course  " + course.getCourseName() + "Successfully!</h3>", true);
			}
			else {
				helper.setSubject("Notification for Swapping");
				helper.setText("<h3>Swapped Course  " + swap.getCourseName() + "Successfully!</h3>", true);
			}

			javaMailSender.send(msg);
			return true;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}
	
	//view dues
	@Override
	public float viewDues(Payment payment) {
		// TODO Auto-generated method stub
		float pastPaymentsAmount = registrationDaoImpl.pastPaymentsAmount(payment);
		float totalAmount = registrationDaoImpl.totalAmount(payment);
		return totalAmount - pastPaymentsAmount;

	}

	//send email for payment
	@Override
	public void sendEmailForPayment(Payment payment) {
		String emailId;

		try {

			MimeMessage msg = javaMailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			emailId = registrationDaoImpl.getEmail(payment.getId());

			helper.setTo(emailId);

			helper.setSubject("Notification for Payment");
			helper.setText("<h3>Payment  " + payment.getPaymentAmount() + "$ received Successfully!</h3>", true);

			javaMailSender.send(msg);

		} catch (Exception e) {
			System.out.print(e);

		}
	}
	
	//post payment activities
	@Override
	public int postPayment(Payment payment) {
		// TODO Auto-generated method stub
		float due = registrationDaoImpl.totalAmount(payment) - registrationDaoImpl.pastPaymentsAmount(payment);
		if (due > 0) {
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			String strDate = dateFormat.format(date);
			Random rand = new Random();
			int randomId = rand.nextInt(1000000);
			String paymentId = String.valueOf(randomId);
			if (registrationDaoImpl.postPayment(payment, paymentId, strDate) == 1) {
				this.sendEmailForPayment(payment);
				return 0;
			}
			return 2;
		}
		return 1;
	}

	//swap course
	@Override
	public int swapCourse(SwapCourse swapCourse) {
		// TODO Auto-generated method stub

		EnrollObject enrollObject=new EnrollObject();
		enrollObject.setUserId(swapCourse.getUserId());
		enrollObject.setCourseId(swapCourse.getNewCourseId());
		int j=this.enroll(enrollObject);
		if(j==1) {
		enrollObject.setCourseId(swapCourse.getOldCourseId());
		Boolean i=this.dropCourse(enrollObject);
		if(i) 
		return 1;
		
		else {
			enrollObject.setCourseId(swapCourse.getNewCourseId());
			this.dropCourse(enrollObject);
			sendEmail(enrollObject, 3);
			return 0;
		}
		}
		else
			return j;
	}

	//fetch Available Courses
	@Override
	public List<Course> fetchAvailableCourses(SelectCriteria selectCriteria) {
		// TODO Auto-generated method stub
		
		return registrationDaoImpl.fetchAvailableCourses(selectCriteria);
	}
	
	//fetch Not Enrolled Courses
	@Override
	public List<Course> fetchNotEnrolledCourses(User user) {
		// TODO Auto-generated method stub
		
		return registrationDaoImpl.fetchNotEnrolledCourses(user);
	}
	
	//fetch Existing Courses
	@Override
	public List<Course> fetchExistingCourses() {
		// TODO Auto-generated method stub
		return registrationDaoImpl.fetchExistingCourses();
	}
	
}

