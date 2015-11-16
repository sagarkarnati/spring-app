package com.vidya.api.repos;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.vidya.api.db.models.Employee;
import com.vidya.api.db.models.Role;
import com.vidya.api.repository.EmployeeRepository;

@ContextConfiguration(locations = { "classpath:spring-base-test.xml", "classpath:spring-fongo-test.xml" })
public class EmployeeRepositoryTest extends AbstractTestNGSpringContextTests
{
	@Autowired
	private EmployeeRepository employeeRepository;

	@Test(expectedExceptions = { Exception.class })
	public void createCustomerConfiguration_fail() throws Exception
	{
		Employee employee = new Employee();

		employeeRepository.save(employee);
	}

	@Test
	public void createCustomerConfiguration()
	{
		Employee employee = new Employee();
		employee.setUsername("sagarkarnati");
		employee.setPassword("123456");
		employee.setFirstName("Vidya");
		employee.setRoles(Arrays.asList(new Role("Admin","ROLE_ADMIN")));

		employeeRepository.save(employee);
	}
}