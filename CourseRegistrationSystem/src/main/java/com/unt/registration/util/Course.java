package com.unt.registration.util;

import java.util.Date;

public class Course {
	private String courseId;
	private String courseName ;
	private String deptId;
	private String semester;
	private int year ; 
	private Boolean isActive;
	private int amount;
	private Boolean isMandatory;
	private int strength;
	private String degree;
	private String professor;
	private int courseMaxStrength;
	private Date startDate;
	private Date endDate;
	private String days;
	
	
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	private String courseTimings;
	public String getCourseId() {
		return courseId;
	}
	public String getCourseTimings() {
		return courseTimings;
	}
	public void setCourseTimings(String courseTimings) {
		this.courseTimings = courseTimings;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Boolean getIsMandatory() {
		return isMandatory;
	}
	public void setIsMandatory(Boolean isMandatory) {
		this.isMandatory = isMandatory;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public int getCourseMaxStrength() {
		return courseMaxStrength;
	}
	public void setCourseMaxStrength(int courseMaxStrength) {
		this.courseMaxStrength = courseMaxStrength;
	}
}
