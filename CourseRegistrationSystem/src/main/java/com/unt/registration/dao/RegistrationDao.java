package com.unt.registration.dao;

import java.util.List;

import com.unt.registration.util.Department;
import com.unt.registration.util.User;

public interface RegistrationDao {

	public User userValidate(String id);
	public int userExists(String id);
	public int userIdProvided(String id);
	public Boolean signup(User user);
	public List<Department> fetchAllDepartments();
	public Boolean resetPassword(User user);
	public String getEmail(String id);
}
