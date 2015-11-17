package com.vidya.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

import com.vidya.api.db.models.Employee;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository
{
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Employee> findByFirstName(String firstName)
	{
		Criteria criteria = Criteria.where("firstName").is(firstName);
		Query query = new Query(criteria);

		return mongoTemplate.find(query, Employee.class);
	}

	@Override
	public List<Employee> search(String text)
	{
		TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(text);
		Query query = TextQuery.queryText(criteria).sortByScore().with(new PageRequest(0, 15));

		return mongoTemplate.find(query, Employee.class);
	}

	@Override
	public void save(Employee employee)
	{
		mongoTemplate.save(employee);		
	}
}
