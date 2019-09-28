package com.unt.registration.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.unt.registration.util.User;

@Repository
public class RegistrationDaoImpl implements RegistrationDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public User getUserDetails() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM \"Registration DB\".\"Userdetails\"";
		return jdbcTemplate.queryForObject(sql, new Object[] {}, new BeanPropertyRowMapper<>(User.class));
	}
}
