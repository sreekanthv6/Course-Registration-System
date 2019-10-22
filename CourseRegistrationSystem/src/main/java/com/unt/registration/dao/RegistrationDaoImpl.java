package com.unt.registration.dao;

import java.sql.Types;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.unt.registration.service.RegistrationServiceImpl;
import com.unt.registration.util.Course;
import com.unt.registration.util.Department;
import com.unt.registration.util.EnrollObject;
import com.unt.registration.util.SelectCriteria;
import com.unt.registration.util.User;

@Repository
public class RegistrationDaoImpl implements RegistrationDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	RegistrationServiceImpl registrationServiceImpl;

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
		Object[] args = { user.getId(), user.getFirstName(), user.getLastName(), user.getEmail().toLowerCase(),
				user.getMobile(), user.getDeptId(), 0, user.getPassword() };
		int[] argTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.NUMERIC, Types.VARCHAR,
				Types.NUMERIC, Types.VARCHAR };
		if (jdbcTemplate.update(sql, args, argTypes) == 1)
			return true;
		else
			return false;
	}

	@Override
	public List<Department> fetchAllDepartments() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM \"Registration DB\".\"Departments\"";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Department>(Department.class));
	}

	@Override
	public String getEmail(String id) {
		String sql = "SELECT email FROM \"Registration DB\".\"Userdetails\" where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, String.class);
	}

	@Override
	public Boolean resetPassword(User user) {
		// TODO Auto-generated method stub
		String sql = "UPDATE \"Registration DB\".\"Userdetails\" SET password=? where id=?";
		Object[] args = { user.getPassword(), user.getId() };
		int[] argTypes = { Types.VARCHAR, Types.VARCHAR };
		if (jdbcTemplate.update(sql, args, argTypes) == 1)
			return true;
		else
			return false;
	}

	public String getCurrentSemester() {
		String currentSemester;
		if ((Calendar.getInstance().get(Calendar.MONTH)) >= 1 && (Calendar.getInstance().get(Calendar.MONTH)) <= 4)
			currentSemester = "Spring";
		else if ((Calendar.getInstance().get(Calendar.MONTH)) >= 5 && (Calendar.getInstance().get(Calendar.MONTH)) <= 7)
			currentSemester = "Summer";
		else
			currentSemester = "Fall";
		return currentSemester;
	}

	@Override
	public List<Course> getCourses(SelectCriteria selectCriteria) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM \"Registration DB\".\"Courses\" where degree=? and \"deptId\"=? and \"isActive\"=? and semester=? and year=?";
		Object[] args = { selectCriteria.getDegree(), selectCriteria.getDeptId(), true, this.getCurrentSemester(),
				Calendar.getInstance().get(Calendar.YEAR) };
		return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<Course>(Course.class));
	}

	@Override
	public Course findCourse(String courseId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM \"Registration DB\".\"Courses\" where \"courseId\"=? and semester=? and year=?";
		return jdbcTemplate.queryForObject(sql,
				new Object[] { courseId, this.getCurrentSemester(), Calendar.getInstance().get(Calendar.YEAR) },
				new BeanPropertyRowMapper<>(Course.class));
	}

	public List<Course> fetchEnrolledCourses(User user) {
		String sql = "select * from \"Registration DB\".\"Enrollments\" JOIN \"Registration DB\".\"Courses\" ON \"Registration DB\".\"Enrollments\".\"courseId\"=\"Registration DB\".\"Courses\".\"courseId\" where \"Registration DB\".\"Enrollments\".id=?";
		Object[] args = { user.getId() };
		return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<Course>(Course.class));
	}

	@Override
	public boolean dropCourse(EnrollObject enrollObject) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM \"Registration DB\".\"Enrollments\" WHERE id = '" + enrollObject.getUserId()
				+ "' AND \"courseId\" = '" + enrollObject.getCourseId() + "' and semester='" + this.getCurrentSemester()
				+ "'AND year=" + Calendar.getInstance().get(Calendar.YEAR);
		int rows = jdbcTemplate.update(sql);
		boolean strength = decreaseStrength(enrollObject.getCourseId());
		if (rows > 0 && strength) {
			return true;

		} else
			return false;
	}

	@Override
	public boolean decreaseStrength(String id) {
		String sql = "update \"Registration DB\".\"Courses\" set strength = strength-1 where \"courseId\"='" + id
				+ "' and semester='" + this.getCurrentSemester() + "'AND year="
				+ Calendar.getInstance().get(Calendar.YEAR);
		int rows = jdbcTemplate.update(sql);
		if (rows > 0)
			return true;
		else
			return false;

	}

	public int checkStrength(String courseId) {
		String sql = "SELECT count(*)   FROM \"Registration DB\".\"Courses\" where \"Registration DB\".\"Courses\".\"Strength\"  < \"Registration DB\".\"Courses\".\"CourseMaxStrength\" AND \"Registration DB\".\"Courses\".\"CourseID\"='"
				+ courseId + "'";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public boolean increaseStrength(String id) {
		String sql = "update \"Registration DB\".\"Courses\" set strength = strength+1 where \"courseID\"='" + id + "'";
		int rows = jdbcTemplate.update(sql);
		if (rows > 0)
			return true;
		else
			return false;

	}

	@Override
	public String enroll(EnrollObject enrollObject) {
		// TODO Auto-generated method stub
		int i;
		String id = getPrerequisites(enrollObject.getCourseId());
		if (id != "empty") {
			i = checkPrerequisites(enrollObject.getUserId(), id);
		} else
			i = 1;
		if (i > 0) {
			int checkStrength = checkStrength(enrollObject.getCourseId());
			if (checkStrength > 0) {
				String sql = "INSERT INTO  \"Registration DB\".\"Enrollments\" values(?,?,?,?,?)";
				Object[] args = { enrollObject.getUserId(), enrollObject.getCourseId(), this.getCurrentSemester(),
						Calendar.getInstance().get(Calendar.YEAR), null };
				int[] argTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.NUMERIC, Types.CHAR };
				if (jdbcTemplate.update(sql, args, argTypes) == 1) {
					increaseStrength(enrollObject.getUserId());
					return "Enrolled";
				} else
					return "NotEnrolled";
			}

			else
				return "StrengthFull";
		} else

			return "NoPrerequisites";
	}

	@Override
	public int checkPrerequisites(String userId, String courseId) {
//		String id=getPrerequisites(courseId);
		String sql = "SELECT COUNT(*) FROM \"Registration DB\".\"Enrollments\" where id='" + userId
				+ "' AND \"courseId\"='" + courseId + "' AND grade!=null";
		return jdbcTemplate.queryForObject(sql, Integer.class);

	}

	@Override
	public String getPrerequisites(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT \"prerequisiteId\" FROM \"Registration DB\".\"Prerequisites\" where \"courseId\"='" + id
				+ "'";
		if (jdbcTemplate.queryForObject(sql, String.class) != null)
			return jdbcTemplate.queryForObject(sql, String.class);
		else
			return "empty";

	}

}
