package com.unt.registration.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.unt.registration.service.RegistrationServiceImpl;
import com.unt.registration.util.User;

@RestController
//@RequestMapping("/RegistrationController")
public class RegistrationController {
	/*
	 * public static void main(String[] args) {
	 * SpringApplication.run(RegistrationController.class, args); }
	 */
    @GetMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println(request.getParameter("uname"));
		RequestDispatcher rd=request.getRequestDispatcher("student_index.html");
		rd.forward(request, response);
    }
		
	@GetMapping("/add")
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("select_degree.jsp");
		rd.include(request, response);
		
    	
    	
    }
	@PostMapping("/search")
	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}