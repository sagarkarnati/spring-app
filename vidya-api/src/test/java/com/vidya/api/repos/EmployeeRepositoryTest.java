package com.vidya.api.repos;

import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import com.vidya.api.models.Employee;
import com.vidya.api.repository.EmployeeRepository;

//@ContextConfiguration(locations = { "classpath:spring-base-test.xml"})
public class EmployeeRepositoryTest extends AbstractTestNGSpringContextTests {
	
	//@Autowired
	private EmployeeRepository employeeRepository;
	
	//@Test
	public void createCustomerConfiguration()
	{
		Employee employee = new Employee("Vidya");
		employeeRepository.save(employee);
	}
}