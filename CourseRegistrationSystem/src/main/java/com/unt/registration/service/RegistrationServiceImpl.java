package com.unt.registration.service;

import java.util.List;

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
import com.unt.registration.util.SelectCriteria;
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
	public String enroll(EnrollObject enrollObject) {
		// TODO Auto-generated method stub
		if(registrationDaoImpl.enroll(enrollObject)=="Enrolled") {
			sendEmail(enrollObject, 1);
			return "Enrolled";
		}
		return registrationDaoImpl.enroll(enrollObject);
	}

	@Override
	public List<Course> fetchEnrolledCourses(User user) {
		// TODO Auto-generated method stub
		return registrationDaoImpl.fetchEnrolledCourses(user);
	}

	@Override
	public boolean dropCourse(EnrollObject enrollObject) {
		// TODO Auto-generated method stub

		if (registrationDaoImpl.dropCourse(enrollObject)) {
			sendEmail(enrollObject,1);
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
			}
			else {
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

}
