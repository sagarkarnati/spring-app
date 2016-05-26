package com.vidya.api.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vidya.api.view.models.EmployeeDTO;

/**
 * Rest Controller for the Employee CURD Operations. 
 * 
 * @author sagarkarnati
 *
 */
@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController
{
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeRestController.class);

	/**
	 * Get Employee
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public @ResponseBody EmployeeDTO getEmployee(@PathVariable("id") String id)
	{
		LOG.info("getEmployee request received for with and id {}",id);
		
		EmployeeDTO employee = new EmployeeDTO();
		employee.setUsername("sagarkarnati");
		employee.setFirstName("Vidya");
		employee.setMiddleName("Sagar");
		employee.setLastName("Karnati");
		employee.setId(id);

		return employee;
	}

	/**
	 * Create Employee
	 * 
	 * @param employee
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void addEmployee(@RequestBody EmployeeDTO employee)
	{
		LOG.info("addEmployee request received {}",employee);
	}

	/**
	 * Update employee
	 * 
	 * @param id
	 * @param employee
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public void updateEmployee(@PathVariable("id") String id,@RequestBody EmployeeDTO employee)
	{
		LOG.info("updateEmployee request received for id {} and employee {}",id, employee);
	}

	/**
	 * Delete Employee
	 * 
	 * @param id
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable("id") String id)
	{
		LOG.info("deleteEmployee request received for id {}",id);
	}
	
	/**
	 * Upload employee profile
	 * 
	 * @param id
	 * @param name
	 * @param file
	 */
	@RequestMapping(value = "/{id}/profile", method = RequestMethod.POST)
	public void handleFileUpload(@PathVariable("id") String id,
			@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file)
	{
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				//BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
				//stream.write(bytes);
				//stream.close();
				LOG.info("You successfully uploaded {} !",name);
			} catch (Exception e) {
				LOG.error("You failed to upload {} => " + e.getMessage(),e);
			}
		} else {
			LOG.error("You failed to upload file {} because the file was empty.",name);
		}
	}
	
	/**
	 * Download employee profile
	 * 
	 * @param id
	 * @param name
	 * @param file
	 */
	@RequestMapping(value = "/{id}/profile", method = RequestMethod.GET)
	public InputStreamResource handleFileDownload(@PathVariable("id") String fileName) {
	    
		return new InputStreamResource(new ByteArrayInputStream("This is the test file content".getBytes()));
	}	
}