package com.unt.registration.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.unt.registration.util.User;

public class RegistrationServiceImplTest {

	
	
	@Test
	public void testUserValidate() {
		RegistrationServiceImpl registrationServiceImpl=new RegistrationServiceImpl();
		User user,expected;
		user=registrationServiceImpl.userValidate("0001", "qwerty");
		expected=null;
		assertEquals("expected", "user");
		fail("Not yet implemented");
	}

	@Test
	public void testSignup() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchAllDepartments() {
		fail("Not yet implemented");
	}

	@Test
	public void testResetPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCourses() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindCourse() {
		fail("Not yet implemented");
	}

	@Test
	public void testEnroll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchEnrolledCourses() {
		fail("Not yet implemented");
	}

	@Test
	public void testDropCourse() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewDues() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendEmailForPayment() {
		fail("Not yet implemented");
	}

	@Test
	public void testPostPayment() {
		fail("Not yet implemented");
	}

	@Test
	public void testSwapCourse() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchAvailableCourses() {
		fail("Not yet implemented");
	}

}
