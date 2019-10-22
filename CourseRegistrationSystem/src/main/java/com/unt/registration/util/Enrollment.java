package com.unt.registration.util;

public class Enrollment {
private String id;
private String courseId;
private String semester;
private int year;
private char grade;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getCourseId() {
	return courseId;
}
public void setCourseId(String courseId) {
	this.courseId = courseId;
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
public char getGrade() {
	return grade;
}
public void setGrade(char grade) {
	this.grade = grade;
}
}
