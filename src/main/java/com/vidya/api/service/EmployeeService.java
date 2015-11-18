package com.vidya.api.service;

import java.util.List;

import com.vidya.api.db.models.Employee;
import com.vidya.api.view.models.EmployeeDTO;

public interface EmployeeService
{
	public List<Employee> getEmployeeByFirstName(String firstName);

	public List<Employee> search(String name);

	public void saveEmployee(EmployeeDTO employee);

	public void deleteEmployee(String id);
}
