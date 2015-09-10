package com.vidya.api.models;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.Indexed;

public class User extends BaseModel
{
	@NotNull
	@Size(min = 4)
	@Indexed(unique = true)
	private String username;

	@NotNull
	private String password;

	@NotEmpty
	private List<Role> roles;

	@NotNull
	private long expires;

	public User()
	{

	}

	public User(String username)
	{
		this.username = username;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public List<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(List<Role> roles)
	{
		this.roles = roles;
	}

	public long getExpires()
	{
		return expires;
	}

	public void setExpires(long expires)
	{
		this.expires = expires;
	}
}
