package com.vidya.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidya.api.db.models.Employee;
import com.vidya.api.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee getEmployeeByFirstName(String firstName)
	{
		return employeeRepository.findByFirstName(firstName);
	}
}