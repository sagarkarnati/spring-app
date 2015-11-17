package com.vidya.api.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vidya.api.db.models.Employee;
import com.vidya.api.db.models.Role;
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
		Employee employee = new Employee();
		employee.setUsername("sagarkarnati");
		employee.setPassword("123456");
		employee.setFirstName("Vidya");
		employee.setRoles(Arrays.asList(new Role("Admin","ROLE_ADMIN")));
		
		return employee;
	}

	@POST
	@RequestMapping("/employee")		
	public Employee addEmployee(@RequestBody @Valid Employee employee)
	{
		return employee;
	}

	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public @ResponseBody String handleFileUpload(@RequestParam("name") String name,@RequestParam("file") MultipartFile file){
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = 
						new BufferedOutputStream(new FileOutputStream(new File(name)));
				stream.write(bytes);
				stream.close();
				return "You successfully uploaded " + name + "!";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name + " because the file was empty.";
		}
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public List<Employee> searchEmployee(@NotBlank @RequestParam("text") String name)
	{
		return employeeService.search(name);
	}
}