package com.vidya.api.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vidya.api.db.models.Employee;
import com.vidya.api.view.models.EmployeeDTO;

@Component
public class EmployeeDTOConverter implements Converter<Employee, EmployeeDTO>
{
	@Override
	public EmployeeDTO convert(Employee source)
	{
		EmployeeDTO employeeDTO = new EmployeeDTO();
		
		employeeDTO.setId(source.getId());
		employeeDTO.setUsername(source.getUsername());
		employeeDTO.setFirstName(source.getFirstName());
		employeeDTO.setMiddleName(source.getMiddleName());
		employeeDTO.setLastName(source.getLastName());

		
		return employeeDTO;
	}
}