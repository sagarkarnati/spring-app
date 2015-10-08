package com.vidya.api.controller;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vidya.api.db.models.Employee;
import com.vidya.api.service.EmployeeService;

@RestController
@RequestMapping("/custom")
public class EmployeeRestController
{
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeRestController.class);

	@Autowired
	private EmployeeService employeeService;

	@GET
	@RequestMapping("/testEmployee")
	@PreAuthorize("hasRole('ROLE_USER')")
	public Employee greeting(@RequestParam(value = "name", defaultValue = "World") String name)
	{
		LOG.info("Reached here");;

		return new Employee(name);
	}

	@POST
	@RequestMapping("/employee")
	public Employee addEmployee(@Valid Employee employee)
	{
		return employee;
	}
}