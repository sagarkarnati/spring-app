package com.vidya.api.controller;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;


@Test
public class EmployeeRestControllerIT
{
	@BeforeClass
	public void setup()
	{
		RestAssured.baseURI = "http://localhost:8080";
	}
	
	@Test
	public void testaddEmployee()
	{
		given().queryParameter("name", "sagarkarnati").get("/api/employee/testEmployee").then().body("username", equalTo("sagarkarnati"));
	}
}
