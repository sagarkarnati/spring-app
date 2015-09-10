package com.vidya.api.service;

import com.vidya.api.models.Employee;

public interface EmployeeService
{
	public Employee getEmployeeByFirstName(String firstName);
}
