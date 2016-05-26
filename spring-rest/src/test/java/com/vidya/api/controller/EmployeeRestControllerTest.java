package com.vidya.api.controller;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.vidya.api.view.models.EmployeeDTO;
import com.vividsolutions.jts.io.ByteArrayInStream;

/**
 * Unit test for Employee Rest Controller
 * 
 * @author sagarkarnati
 *
 */
@Test
public class EmployeeRestControllerTest
{
	/**
	 * Test for get request
	 * 
	 */
	@Test
	public void test_getEmployee()
	{
		given().
				standaloneSetup(new EmployeeRestController()).				
		when().
				get("/api/employee/1").
		then().
				statusCode(200)
				.body("id", equalTo("1"))
				   .body("username", equalTo("sagarkarnati"))
				   .body("firstName", equalTo("Vidya"))
				   .body("middleName", equalTo("Sagar"))
				   .body("lastName", equalTo("Karnati"));
	}
	
	/**
	 * Test for post request
	 * 
	 */
	@Test
	public void test_addEmployee()
	{
		EmployeeDTO employee = new EmployeeDTO();
		employee.setId(1+"");
		employee.setUsername("testUser");
		employee.setFirstName("test");
		employee.setMiddleName("user");
		employee.setLastName("lastname");		
		
		given()
			.standaloneSetup(new EmployeeRestController())	
			.contentType(ContentType.JSON)
			.body(employee)
		.when()
			.post("/api/employee")
	   .then()
		   .statusCode(200);
	}
	
	/**
	 * Test for put request
	 * 
	 */
	@Test
	public void test_updateEmployee()
	{
		EmployeeDTO employee = new EmployeeDTO();
		employee.setId(1+"");
		employee.setUsername("testUser_updated");
		employee.setFirstName("test_updated");
		employee.setMiddleName("user_updated");
		employee.setLastName("lastname_updated");		
		
		given()
			.standaloneSetup(new EmployeeRestController())	
			.contentType(ContentType.JSON)
			.body(employee)
		.when()
			.put("/api/employee/1")
	   .then()
		   .statusCode(200);
	}

	/**
	 * Test for delete request
	 * 
	 */
	@Test
	public void test_deleteEmployee()
	{
		given()
			.standaloneSetup(new EmployeeRestController())						
		.when()
			.delete("/api/employee/1")
	   .then()
		   .statusCode(200);
	}
	
	/**
	 * Test for upload file
	 * 
	 */
	@Test
	public void test_uploadProfile()
	{
		InputStream io = new ByteArrayInputStream("This is the test file content".getBytes());
		
		given()
			.standaloneSetup(new EmployeeRestController())
			.multiPart("file","EmployeeProfile.txt",io)
			.queryParam("name", "EmployeeProfile.txt")
		.when()			
			.post("/api/employee/1/profile")
	   .then()
		   .statusCode(200);
	}
	
	/**
	 * Test for upload file
	 * 
	 */
	@Test
	public void test_downloadProfile()
	{
		given()
			.standaloneSetup(new EmployeeRestController())			
		.when()		
			.get("/api/employee/1/profile")
	   .then()
		   .statusCode(200);
	}
}