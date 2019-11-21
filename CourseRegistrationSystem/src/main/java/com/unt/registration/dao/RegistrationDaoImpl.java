package com.unt.registration.dao;

import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.unt.registration.service.RegistrationServiceImpl;
import com.unt.registration.util.Course;
import com.unt.registration.util.Department;
import com.unt.registration.util.EnrollObject;
import com.unt.registration.util.Enrollment;
import com.unt.registration.util.Grade;
import com.unt.registration.util.Payment;
import com.unt.registration.util.SelectCriteria;
import com.unt.registration.util.SwapCourse;
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
		String sql = "select * from \"Registration DB\".\"Enrollments\" JOIN \"Registration DB\".\"Courses\" ON \"Registration DB\".\"Enrollments\".\"courseId\"=\"Registration DB\".\"Courses\".\"courseId\" where \"Registration DB\".\"Enrollments\".id=? and \"Registration DB\".\"Enrollments\".semester=? and \"Registration DB\".\"Enrollments\".year=?";
		Object[] args = { user.getId(), this.getCurrentSemester(), Calendar.getInstance().get(Calendar.YEAR) };
		return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<Course>(Course.class));
	}

	@Override
	public int dropCourse(EnrollObject enrollObject) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM \"Registration DB\".\"Enrollments\" WHERE id = '" + enrollObject.getUserId()
				+ "' AND \"courseId\" = '" + enrollObject.getCourseId() + "' and semester='" + this.getCurrentSemester()
				+ "'AND year=" + Calendar.getInstance().get(Calendar.YEAR);
		return jdbcTemplate.update(sql);
	}

	@Override
	public int decreaseStrength(String id) {
		String sql = "update \"Registration DB\".\"Courses\" set strength = strength-1 where \"courseId\"='" + id
				+ "' and semester='" + this.getCurrentSemester() + "'AND year="
				+ Calendar.getInstance().get(Calendar.YEAR);
		return jdbcTemplate.update(sql);
	}

	public int checkStrength(String courseId) {
		String sql = "SELECT count(*)   FROM \"Registration DB\".\"Courses\" where \"Registration DB\".\"Courses\".\"strength\"  < \"Registration DB\".\"Courses\".\"courseMaxStrength\" AND \"Registration DB\".\"Courses\".\"courseId\"='"
				+ courseId + "'";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public int increaseStrength(String courseId) {
		String sql = "update \"Registration DB\".\"Courses\" set strength = strength+1 where \"courseId\"='" + courseId + "'";
		return jdbcTemplate.update(sql);

	}

	@Override
	public int enroll(EnrollObject enrollObject) {
		// TODO Auto-generated method stub
				String sql = "INSERT INTO  \"Registration DB\".\"Enrollments\" values(?,?,?,?,?)";
				Object[] args = { enrollObject.getUserId(), enrollObject.getCourseId(), this.getCurrentSemester(),
						Calendar.getInstance().get(Calendar.YEAR), null };
				int[] argTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.NUMERIC, Types.CHAR };
				return jdbcTemplate.update(sql, args, argTypes);
					
	}

	@Override
	public int checkPrerequisites(String userId, String courseId) {
		String sql = "SELECT COUNT(*) FROM \"Registration DB\".\"Enrollments\" where id='" + userId
				+ "' AND \"courseId\"='" + courseId + "' AND grade is not NULL";
		return jdbcTemplate.queryForObject(sql, Integer.class);

	}

	@Override
	public String getPrerequisites(String courseId) {
		// TODO Auto-generated method stub
		String sql = "SELECT \"prerequisiteId\" FROM \"Registration DB\".\"Prerequisites\" where \"courseId\"='" + courseId
				+ "'";
			return jdbcTemplate.queryForObject(sql, String.class);

	}
	@Override
	public int ifPrerequisitesExist(String courseId) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) FROM \"Registration DB\".\"Prerequisites\" where \"courseId\"='" + courseId
				+ "'";
		return jdbcTemplate.queryForObject(sql, Integer.class);

	}

	@Override
	public int postPayment(Payment payment, String paymentId, String strDate) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO \"Registration DB\".\"Payments\" values(?,?,?,?)";
		Object[] args = { payment.getId(), paymentId, strDate,
				payment.getPaymentAmount() };
		int[] argTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.NUMERIC };
		return jdbcTemplate.update(sql, args, argTypes);
	}
	@Override
	public List<Payment> pastPayments(User user) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM \"Registration DB\".\"Payments\" where id=?";
		return (List<Payment>) jdbcTemplate.query(sql, new Object[] { user.getId() },
				new BeanPropertyRowMapper<Payment>(Payment.class));

	}

	@Override
	public List<Grade> viewGrades(User user) {
		// TODO Auto-generated method stub
		String sql = "select * from \"Registration DB\".\"Enrollments\" JOIN \"Registration DB\".\"Courses\" ON \"Registration DB\".\"Enrollments\".\"courseId\"=\"Registration DB\".\"Courses\".\"courseId\" where \"Registration DB\".\"Enrollments\".id=? and grade IS NOT NULL";
		
		return (List<Grade>) jdbcTemplate.query(sql,new Object[] { user.getId() },
				new BeanPropertyRowMapper<Grade>(Grade.class));
	}
	@Override
	public int pastPaymentsAmount(Payment payment) {
		// TODO Auto-generated method stub
		String sql="select sum(\"paymentAmount\") from \"Registration DB\".\"Payments\" where id='"+payment.getId()+"'";
		return jdbcTemplate.queryForObject(sql,  Integer.class);
	}

	@Override
	public int totalAmount(Payment payment) {
		// TODO Auto-generated method stub
		String sql="select sum(amount) from \"Registration DB\".\"Enrollments\" JOIN \"Registration DB\".\"Courses\" ON \"Registration DB\".\"Enrollments\".\"courseId\"=\"Registration DB\".\"Courses\".\"courseId\" where \"Registration DB\".\"Enrollments\".id='"+payment.getId()+"'";
		return jdbcTemplate.queryForObject(sql,  Integer.class);
	}

	@Override
	public List<Course> mandatoryCoursesDone(User user) {
		// TODO Auto-generated method stub
		String sql="select * from \"Registration DB\".\"Enrollments\" JOIN \"Registration DB\".\"Courses\" ON"
				+ " \"Registration DB\".\"Enrollments\".\"courseId\"=\"Registration DB\".\"Courses\".\"courseId\" where "
				+ "\"Registration DB\".\"Courses\".\"isMandatory\"=true AND \"Registration DB\".\"Enrollments\".id='"+user.getId()+"'"
				+"AND \"Registration DB\".\"Courses\".\"deptId\"='"+user.getDeptId()+"'";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Course>(Course.class));
	}

	@Override
	public List<Course> mandatoryCoursesNotDone(User user) {
		// TODO Auto-generated method stub
		String sql="select * from  \"Registration DB\".\"Courses\"  \r\n" + 
				" \r\n" + 
				"where \"Registration DB\".\"Courses\".\"isMandatory\"=true AND (\r\n" + 
				"														  \"Registration DB\".\"Courses\".\"deptId\"=?) and \"Courses\".\"courseId\"\r\n" + 
				"		NOT IN(select \"Courses\".\"courseId\" from \"Registration DB\".\"Enrollments\" JOIN \r\n" + 
				"			   \"Registration DB\".\"Courses\" ON \"Registration DB\".\"Enrollments\".\"courseId\"=\"Registration DB\".\"Courses\".\"courseId\" where \r\n" + 
				"				\"Registration DB\".\"Courses\".\"isMandatory\"=true AND \"Registration DB\".\"Enrollments\".id=?);";
					
		return jdbcTemplate.query(sql,new Object[] { user.getDeptId(), user.getId() }, new BeanPropertyRowMapper<Course>(Course.class));
	}

	@Override
	public List<Course> fetchAvailableCourses(SelectCriteria selectCriteria) {
		// TODO Auto-generated method stub
		String sql="select * from \"Registration DB\".\"Courses\" where \"Registration DB\".\"Courses\".\"deptId\"=? and \"Registration DB\".\"Courses\".\"degree\"=? and \"Registration DB\".\"Courses\".\"isActive\"=true\r\n" + 
				"and \"Registration DB\".\"Courses\".\"courseId\" NOT IN(select \"Registration DB\".\"Enrollments\".\"courseId\" from \"Registration DB\".\"Enrollments\"\r\n" + 
				"where \"Registration DB\".\"Enrollments\".\"id\"=?)";
		Object[] args = { selectCriteria.getDeptId(),selectCriteria.getDegree(),selectCriteria.getUserId()};
		return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<Course>(Course.class));
	}
	@Override
	public List<Course> fetchNotEnrolledCourses(User user) {
		// TODO Auto-generated method stub
		String sql="select * from \"Registration DB\".\"Courses\" where \"Registration DB\".\"Courses\".\"isActive\"=true\r\n" + 
				"and \"Registration DB\".\"Courses\".\"courseId\" NOT IN(select \"Registration DB\".\"Enrollments\".\"courseId\" from \"Registration DB\".\"Enrollments\"\r\n" + 
				"where \"Registration DB\".\"Enrollments\".\"id\"=?)";
		Object[] args = { user.getId()};
		return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<Course>(Course.class));
	}
	@Override
	public int addCourse(Course course) {
		// TODO Auto-generated method stub
		System.out.println(course.getAmount());
		String sql = "INSERT INTO \"Registration DB\".\"Courses\" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] args = { course.getCourseId(), course.getCourseName(), course.getDeptId(), this.getCurrentSemester(),
				Calendar.getInstance().get(Calendar.YEAR), true, course.getIsMandatory(),course.getStrength(),course.getDegree(),course.getProfessor(),
				course.getCourseMaxStrength(),course.getCourseTimings(),course.getDays(),course.getStartDate(),course.getEndDate(), course.getAmount()};
		int[] argTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, 
				Types.NUMERIC, Types.BOOLEAN, Types.BOOLEAN,Types.NUMERIC,Types.VARCHAR,Types.VARCHAR,
				 Types.NUMERIC, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.NUMERIC};
		return jdbcTemplate.update(sql, args, argTypes);
		
	}
	@Override
	public List<Course> fetchExistingCourses() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM \"Registration DB\".\"Courses\"";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Course>(Course.class));
		
	}
	
			@Override
			public int deleteCourse(String courseId) {
				// TODO Auto-generated method stub
				String sql = "delete from \"Registration DB\".\"Courses\" where \"courseId\"=?";
				Object[] args = {courseId};
				int[] argTypes = { Types.VARCHAR};
				return jdbcTemplate.update(sql, args, argTypes);
				
			}
}
