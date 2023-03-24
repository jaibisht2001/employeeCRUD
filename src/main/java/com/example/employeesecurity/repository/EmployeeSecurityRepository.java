package com.example.employeesecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeesecurity.model.EmployeeLogIn;

public interface EmployeeSecurityRepository extends JpaRepository<EmployeeLogIn,String> {

}
