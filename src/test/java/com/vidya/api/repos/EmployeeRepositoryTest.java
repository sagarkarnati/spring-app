package com.vidya.api.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.vidya.api.models.Employee;
import com.vidya.api.repository.EmployeeRepository;

@ContextConfiguration(locations = { "classpath:spring-base-test.xml","classpath:spring-fongo.xml" })
public class EmployeeRepositoryTest extends AbstractTestNGSpringContextTests
{

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void createCustomerConfiguration()
	{
		Employee employee = new Employee("Vidya");
		employeeRepository.save(employee);
	}
}