package com.vidya.api.service;

import java.util.List;

import com.vidya.api.db.models.Employee;

public interface EmployeeService
{
	public List<Employee> getEmployeeByFirstName(String firstName);

	public List<Employee> search(String name);
}
