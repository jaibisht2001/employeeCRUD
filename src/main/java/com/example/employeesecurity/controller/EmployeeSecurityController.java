package com.example.employeesecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeesecurity.jwt.JwtUtills;
import com.example.employeesecurity.model.EmployeeLogIn;
import com.example.employeesecurity.repository.EmployeeSecurityRepository;
import com.example.employeesecurity.security.EmployeeLogInDetailService;
import com.example.employeesecurity.security.EmployeeLogInDetails;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeSecurityController {
	
	@Autowired
	EmployeeSecurityRepository employeesecurityrepo;
	
	@Autowired
	AuthenticationManager authmanager;
	
	@Autowired
	JwtUtills jwtutills;
	
	@Autowired
	EmployeeLogInDetailService emplogindetailservice;
	
	@PostMapping("/add")
	
	public String addEmployee(@RequestBody EmployeeLogIn employeelogin)
	{
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();//encryption method :password string to encryption
		String enpassword=encoder.encode(employeelogin.getPassword());
		employeelogin.setPassword(enpassword);
		employeesecurityrepo.save(employeelogin);
		return "Added";
	}
	
	@PostMapping("/authenticate")
	public String authenticateEmployee(@RequestBody EmployeeLogIn employeelogin) {
		Authentication authentication= authmanager.authenticate(new UsernamePasswordAuthenticationToken(employeelogin.getUsername(),employeelogin.getPassword()));
	    if(authentication.isAuthenticated()) {
	    	EmployeeLogInDetails  emplogindetails=(EmployeeLogInDetails) emplogindetailservice.loadUserByUsername(employeelogin.getUsername());
	    	String TOKEN=jwtutills.generateJwtToken(emplogindetails);
	    	return TOKEN;
	    }
		
		return null;
	}

}
