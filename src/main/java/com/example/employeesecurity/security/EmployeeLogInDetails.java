package com.example.employeesecurity.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.employeesecurity.model.EmployeeLogIn;


public class EmployeeLogInDetails implements UserDetails {

	EmployeeLogIn employeelogin=new EmployeeLogIn();
	
	
	public EmployeeLogInDetails(EmployeeLogIn employeelogin) {
		super();
		this.employeelogin = employeelogin;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Collections.singleton(new SimpleGrantedAuthority("EMPLOYEE"));
	}

	@Override
	public String getPassword() {
	
		return employeelogin.getPassword();
	}

	@Override
	public String getUsername() {
	
		return employeelogin.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
	
		return true;
	}

}
