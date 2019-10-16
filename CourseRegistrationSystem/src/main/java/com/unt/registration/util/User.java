package com.unt.registration.util;

public class User {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String deptId;
	private String mobile;
	private String password;
	private int role;
	private Boolean userExists=false;
	private Boolean validUser=false;
	
	public Boolean getUserExists() {
		return userExists;
	}
	public void setUserExists(Boolean userExists) {
		this.userExists = userExists;
	}
	public Boolean getValidUser() {
		return validUser;
	}
	public void setValidUser(Boolean validUser) {
		this.validUser = validUser;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}


}
