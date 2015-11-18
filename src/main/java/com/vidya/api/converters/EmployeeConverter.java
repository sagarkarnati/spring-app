package com.vidya.api.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vidya.api.db.models.Employee;
import com.vidya.api.view.models.EmployeeDTO;

@Component
public class EmployeeConverter implements Converter<EmployeeDTO, Employee>
{
	@Override
	public Employee convert(EmployeeDTO source)
	{
		Employee employee = new Employee();
		
		employee.setId(source.getId());
		employee.setUsername(source.getUsername());
		employee.setFirstName(source.getFirstName());
		employee.setMiddleName(source.getMiddleName());
		employee.setLastName(source.getLastName());
		
		return employee;
	}
}
