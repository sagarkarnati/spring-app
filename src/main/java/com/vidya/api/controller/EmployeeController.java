package com.vidya.api.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.vidya.api.models.Employee;

@EnableWebMvc
@RepositoryRestResource
public interface EmployeeController extends PagingAndSortingRepository<Employee, ObjectId>
{
	List<Employee> findByLastName(@Param("name") String name);
	
	
	
	
	
}
