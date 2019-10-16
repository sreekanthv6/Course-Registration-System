package com.unt.registration.service;

import java.util.List;

import com.unt.registration.util.Department;
import com.unt.registration.util.User;

public interface RegistrationService {
	public User userValidate(String id, String password);
	public String signup(User user);
	public List<Department> fetchAllDepartments();
	public String resetPassword(User user);
}
