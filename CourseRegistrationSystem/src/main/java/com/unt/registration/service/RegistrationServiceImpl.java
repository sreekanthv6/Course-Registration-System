package com.unt.registration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unt.registration.dao.RegistrationDaoImpl;
import com.unt.registration.util.Department;
import com.unt.registration.util.User;

@Service
public class RegistrationServiceImpl implements RegistrationService{
	
	@Autowired
	RegistrationDaoImpl registrationDaoImpl;

	@Override
	public User userValidate(String id, String password) {
		// TODO Auto-generated method stub
		User user = new User();
		if (registrationDaoImpl.userExists(id)== 0)
		{
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
		if(registrationDaoImpl.userIdProvided(user.getId())!=0) {
			if(registrationDaoImpl.userExists(user.getId())==0) {
					if(registrationDaoImpl.signup(user)==true)	
						return "signed up";
					else
						return "Unexpected Error";
			}
			else
				return "Already signed up";
		}
		else
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
		if(registrationDaoImpl.userExists(user.getId())!=0) {
			if(user.getEmail().equals(registrationDaoImpl.getEmail(user.getId())))
			{
				if(registrationDaoImpl.resetPassword(user)==true)
					return "Password reset";
				else
					return "Unexpected Error";
			}
			else
				return "invalid email";
				
					
		}
		else
		return "Invalid user ID";
	}
}
