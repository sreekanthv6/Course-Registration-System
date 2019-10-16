package com.unt.registration.util;

public class Course {
	private String courseId;
	private String courseName ;
	private String deptId;
	private String semester;
	private int year ; 
	private Boolean isActive;
	private float amount;
	private Boolean isManadatory;
	private int strength;
	private String degree;
	private String professor;
	private int courseMaNumberxStrength;
	public String getCourseId() {
		return courseId;
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
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Boolean getIsManadatory() {
		return isManadatory;
	}
	public void setIsManadatory(Boolean isManadatory) {
		this.isManadatory = isManadatory;
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
	public int getCourseMaNumberxStrength() {
		return courseMaNumberxStrength;
	}
	public void setCourseMaNumberxStrength(int courseMaNumberxStrength) {
		this.courseMaNumberxStrength = courseMaNumberxStrength;
	}
}
