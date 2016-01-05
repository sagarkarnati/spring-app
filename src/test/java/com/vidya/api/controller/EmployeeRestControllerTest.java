package com.vidya.api.controller;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

@Test
public class EmployeeRestControllerTest
{
	@Test
	public void testaddEmployee()
	{
		given().
				standaloneSetup(new EmployeeRestController()).
				param("name", "sagarkarnati").
		when().
				get("/api/employee/testEmployee").
		then().
				statusCode(200).
				body("username", equalTo("sagarkarnati"));
	}
}
