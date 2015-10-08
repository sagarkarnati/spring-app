package com.vidya.api.db.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Devices
{
	@Id
	private ObjectId id;
	private String name;

	public ObjectId getId()
	{
		return id;
	}

	public void setId(ObjectId id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}
