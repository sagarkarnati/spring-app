package com.vidya.api.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.vidya.api.view.models.EmployeeDTO;


/**
 * Integration test suit for testing rest services
 * 
 * This show how to call the rest services during MAVEN integration tests phase and the test should be more robust 
 * enough to check resource changes that happened with the rest services by checking DB or the downstream systems.
 * 
 * @author sagarkarnati
 *
 */
@Test
public class EmployeeRestControllerIT
{
	@BeforeClass
	public void setup()
	{
		RestAssured.baseURI = "http://localhost:8080/";
	}
	
	@Test
	public void test_getEmployee()
	{
		given()
		.when()
			.get("/api/employee/1")
	   .then()
		   .statusCode(200)
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
	public void test_dowloadProfile()
	{		
		given()
		.when()
			.get("/api/employee/1/profile")
	   .then()
		   .statusCode(200);
	}
}