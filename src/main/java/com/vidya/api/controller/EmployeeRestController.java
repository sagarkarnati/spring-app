package com.vidya.api.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vidya.api.db.models.Employee;
import com.vidya.api.service.EmployeeService;
import com.vidya.api.view.models.EmployeeDTO;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController
{
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(path = "/testEmployee", method = RequestMethod.GET)
	// @PreAuthorize("hasRole('ROLE_USER')")
	public EmployeeDTO greeting(@RequestParam(value = "name", defaultValue = "World") String name)
	{
		EmployeeDTO employee = new EmployeeDTO();
		employee.setUsername(name);

		return employee;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addEmployee(@RequestBody @Valid EmployeeDTO employee)
	{
		employeeService.saveEmployee(employee);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public void updateEmployee(@NotBlank @PathVariable("id") String id, @RequestBody @Valid EmployeeDTO employee)
	{
		employee.setId(id);
		employeeService.saveEmployee(employee);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deleteEmployee(@NotBlank @PathVariable("id") String id)
	{
		employeeService.deleteEmployee(id);
	}

	// FIXME have to add feature to save the file to Grid FX instead of just
	// discarding it.
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file)
	{
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
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

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<Employee> searchEmployee(@NotBlank @RequestParam("text") String name)
	{
		return employeeService.search(name);
	}
}