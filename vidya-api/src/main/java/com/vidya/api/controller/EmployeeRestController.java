package com.vidya.api.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vidya.api.models.Employee;
import com.vidya.api.service.EmployeeService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Api(value = "onlinestore", description = "Operations pertaining to Online Store")
@RestController
@Path("/rest")
public class EmployeeRestController
{
	@Autowired
	private EmployeeService employeeService;
	
	@GET
	@RequestMapping("/employee")
	@ApiOperation(value = "View the Specific info of the product")	
	public Employee greeting(
			@ApiParam(name = "productId", value = "The Id of the product to be viewed", required = true) 
			@RequestParam(value = "name", defaultValue = "World") String name)
	{
		return new Employee(name);
	}
}