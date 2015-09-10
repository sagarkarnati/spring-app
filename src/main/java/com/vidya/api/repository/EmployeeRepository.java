package com.vidya.api.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vidya.api.models.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, ObjectId> {
	public Employee findByFirstName(@Param("firstName") String firstName);
}