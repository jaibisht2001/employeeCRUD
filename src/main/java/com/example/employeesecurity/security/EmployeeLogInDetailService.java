package com.example.employeesecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.employeesecurity.model.EmployeeLogIn;
import com.example.employeesecurity.repository.EmployeeSecurityRepository;

@Service
public class EmployeeLogInDetailService implements UserDetailsService{

	
	@Autowired
	EmployeeSecurityRepository employeesecurityrepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		EmployeeLogIn emplogin=employeesecurityrepo.findById(username).get();
		return new EmployeeLogInDetails(emplogin);
	}

}
