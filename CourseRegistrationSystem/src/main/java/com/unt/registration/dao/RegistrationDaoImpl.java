package com.unt.registration.dao;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.unt.registration.util.Department;
import com.unt.registration.util.User;

@Repository
public class RegistrationDaoImpl implements RegistrationDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public User userValidate(String id) {
		// TODO Auto-generated method stub
		String sql = "select * from \"Registration DB\".\"Userdetails\" where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<>(User.class));
	}

	@Override
	public int userExists(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(*) FROM \"Registration DB\".\"Userdetails\" where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, Integer.class);
	}

	@Override
	public int userIdProvided(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(*) FROM \"Registration DB\".\"Universitydetails\" where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, Integer.class);
	}

	@Override
	public Boolean signup(User user) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO userdetails values(?,?,?,?,?,?,?,?)";
		Object[] args = { user.getId(), user.getFirstName(), user.getLastName(), user.getEmail().toLowerCase(), user.getMobile(),
				user.getDeptId(), 0, user.getPassword() };
		int[] argTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.NUMERIC ,Types.VARCHAR, Types.NUMERIC,
				Types.VARCHAR };
		if (jdbcTemplate.update(sql, args, argTypes) == 1)
			return true;
		else
			return false;
	}

	@Override
	public List<Department> fetchAllDepartments() {
		// TODO Auto-generated method stub
		String sql= "SELECT * FROM \"Registration DB\".\"Departments\"";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Department>(Department.class));
	}
	
	@Override
	public String getEmail(String id) {
		String sql="SELECT email FROM \"Registration DB\".\"Userdetails\" where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, String.class);
	}
	
	@Override
	public Boolean resetPassword(User user) {
		// TODO Auto-generated method stub
		String sql="UPDATE \"Registration DB\".\"Userdetails\" SET password=? where id=?";
		Object[] args= {user.getPassword(), user.getId()};
		int[] argTypes= {Types.VARCHAR, Types.VARCHAR};
		if(jdbcTemplate.update(sql, args, argTypes)==1)
			return true;
		else
		return false;
	}
}
