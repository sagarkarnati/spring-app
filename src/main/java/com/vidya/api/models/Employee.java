package com.vidya.api.models;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.vidya.db.utils.CascadeSave;

public class Employee
{
	@Id
	private ObjectId id;

	@NotNull
	@Size(min=3)
	private String firstName;

	private String middleName;
	private String lastName;
	private Date createdTime;

	@Version
	private Long version;
	@CreatedDate
	private Date createdAt;
	@LastModifiedDate
	private Date lastModified;

	@CreatedBy
	private String createdBy;

	@LastModifiedBy
	private String lastModifiedBy;

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

	public ObjectId getId()
	{
		return id;
	}

	public void setId(ObjectId id)
	{
		this.id = id;
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

	public Date getCreatedTime()
	{
		return createdTime;
	}

	public void setCreatedTime(Date createdTime)
	{
		this.createdTime = createdTime;
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