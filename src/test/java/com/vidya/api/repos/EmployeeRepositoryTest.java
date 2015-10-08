package com.vidya.api.repos;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import scala.Array;

import com.vidya.api.db.models.Employee;
import com.vidya.api.db.models.Role;
import com.vidya.api.repository.EmployeeRepository;

@ContextConfiguration(locations = { "classpath:spring-base-test.xml", "classpath:spring-fongo-test.xml" })
public class EmployeeRepositoryTest extends AbstractTestNGSpringContextTests
{

	@Autowired
	private EmployeeRepository employeeRepository;

//	@Test(expectedExceptions = { Exception.class })
//	public void createCustomerConfiguration_fail() throws Exception
//	{
//		Employee employee = new Employee();
//		employeeRepository.save(employee);
//	}
	
	@Test
	public void createCustomerConfiguration()
	{
		Employee employee = new Employee();
		
		employee.setUsername("username");
		employee.setPassword("b79aca2251469e626132a96ade2e5582d50b588e");
		
		List<Role> roles = new ArrayList<>();
		Role role = new Role();
		List<String> privileges = new ArrayList<>();
		privileges.add("ADMIN");
		role.setPrivileges(privileges);
		roles.add(role);
		
		employee.setRoles(roles);
		employee.setExpires(123456789);

		employee.setFirstName("asdasdasdasd");
		employeeRepository.save(employee);
	}
}