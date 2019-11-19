package com.unt.registration.service;

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

	@Override
	public List<Department> fetchAllDepartments() {
		// TODO Auto-generated method stub
		return registrationDaoImpl.fetchAllDepartments();
	}

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

	@Override
	public List<Course> getCourses(SelectCriteria selectCriteria) {
		// TODO Auto-generated method stub
		return registrationDaoImpl.getCourses(selectCriteria);
	}

	@Override
	public Course findCourse(String courseId) {
		// TODO Auto-generated method stub
		return registrationDaoImpl.findCourse(courseId);
	}

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

	@Override
	public List<Course> fetchEnrolledCourses(User user) {
		// TODO Auto-generated method stub
		return registrationDaoImpl.fetchEnrolledCourses(user);
	}

	@Override
	public boolean dropCourse(EnrollObject enrollObject) {
		// TODO Auto-generated method stub

		if (registrationDaoImpl.dropCourse(enrollObject) > 0
				&& registrationDaoImpl.decreaseStrength(enrollObject.getCourseId()) > 0) {
			sendEmail(enrollObject, 1);
			return true;
		}
		return false;

	}

	@Override
	public boolean sendEmail(EnrollObject enrollObject, int value) {
		String emailId;
		Course course;

		try {

			MimeMessage msg = javaMailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			emailId = registrationDaoImpl.getEmail(enrollObject.getUserId());
			course = registrationDaoImpl.findCourse(enrollObject.getCourseId());

			helper.setTo(emailId);
			if (value == 0) {
				helper.setSubject("Notification for dropping");
				helper.setText("<h3>Dropped Course  " + course.getCourseName() + "Successfully!</h3>", true);
			} else {
				helper.setSubject("Notification for Enrolling");
				helper.setText("<h3>Enrolled Course  " + course.getCourseName() + "Successfully!</h3>", true);
			}

			javaMailSender.send(msg);
			return true;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}

	@Override
	public float viewDues(Payment payment) {
		// TODO Auto-generated method stub
		float pastPaymentsAmount = registrationDaoImpl.pastPaymentsAmount(payment);
		float totalAmount = registrationDaoImpl.totalAmount(payment);
		return totalAmount - pastPaymentsAmount;

	}

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
			JOptionPane.showMessageDialog(null, e);

		}
	}

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

	@Override
	public int swapCourse(SwapCourse swapCourse) {
		// TODO Auto-generated method stub

		EnrollObject enrollObject=new EnrollObject();
		enrollObject.setUserId(swapCourse.getUserId());
		enrollObject.setCourseId(swapCourse.getNewCourseId());
		System.out.println("new course "+enrollObject.getCourseId());
		int j=this.enroll(enrollObject);
		System.out.println("ernoll success "+j);
		if(j==1) {
		enrollObject.setCourseId(swapCourse.getOldCourseId());
		System.out.println("old course "+enrollObject.getCourseId());
		Boolean i=this.dropCourse(enrollObject);
		System.out.println("drp success "+i);
		if(i) 
		return 1;
		
		else {
			enrollObject.setCourseId(swapCourse.getNewCourseId());
			this.dropCourse(enrollObject);
			return 0;
		}
		}
		else
			System.out.println(j);
			return j;
	}

	@Override
	public List<Course> fetchAvailableCourses(SelectCriteria selectCriteria) {
		// TODO Auto-generated method stub
		
		return registrationDaoImpl.fetchAvailableCourses(selectCriteria);
	}
	@Override
	public List<Course> fetchNotEnrolledCourses(User user) {
		// TODO Auto-generated method stub
		
		return registrationDaoImpl.fetchNotEnrolledCourses(user);
	}

}

