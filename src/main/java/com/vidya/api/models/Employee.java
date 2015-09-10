package com.vidya.api.models;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.vidya.db.utils.CascadeSave;

public class Employee extends BaseModel
{
	@NotNull
	@Size(min = 3)
	private String firstName;

	private String middleName;
	private String lastName;

	@DBRef
	@CascadeSave
	private List<Devices> deviceList;

	public Employee()
	{
	}

	public Employee(String firstName)
	{
		this.firstName = firstName;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public List<Devices> getDeviceList()
	{
		return deviceList;
	}

	public void setDeviceList(List<Devices> deviceList)
	{
		this.deviceList = deviceList;
	}

}