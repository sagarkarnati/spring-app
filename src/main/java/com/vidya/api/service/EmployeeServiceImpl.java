package com.vidya.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import com.vidya.api.db.models.Employee;
import com.vidya.api.repository.EmployeeRepository;
import com.vidya.api.view.models.EmployeeDTO;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private Converter<Employee, EmployeeDTO> employeeDTOConverter;
	
	@Autowired
	private Converter<EmployeeDTO, Employee> employeeConverter;
	
	@Override
	public List<Employee> getEmployeeByFirstName(String firstName)
	{
		return employeeRepository.findByFirstName(firstName);
	}

	@Override
	public List<Employee> search(String name)
	{
		return employeeRepository.search(name);
	}

	@Override
	public void saveEmployee(EmployeeDTO employee)
	{
		employeeRepository.save(employeeConverter.convert(employee));
	}

	@Override
	public void deleteEmployee(String id)
	{
		employeeRepository.delete(id);		
	}
}