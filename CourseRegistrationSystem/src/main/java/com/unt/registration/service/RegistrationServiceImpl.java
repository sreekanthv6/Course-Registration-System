package com.unt.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unt.registration.dao.RegistrationDaoImpl;
import com.unt.registration.util.User;

@Service
public class RegistrationServiceImpl implements RegistrationService{
	
	@Autowired
	RegistrationDaoImpl registrationDaoImpl;

	@Override
	public User getUserDetails() {
		// TODO Auto-generated method stub
		return registrationDaoImpl.getUserDetails();
	}
	
	public void printing() {
		System.out.println("yess!");
	}
}
