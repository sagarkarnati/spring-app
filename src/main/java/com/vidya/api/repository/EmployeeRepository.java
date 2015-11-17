package com.vidya.api.repository;

import java.util.List;

import com.vidya.api.db.models.Employee;

public interface EmployeeRepository 
{
	public void save(Employee employee);
	
	public List<Employee> findByFirstName(String firstName);
	
	public List<Employee> search(String text);
}