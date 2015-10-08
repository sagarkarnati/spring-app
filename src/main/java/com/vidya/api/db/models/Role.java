package com.vidya.api.db.models;

import java.util.List;

public class Role{
	
	private String name;
	
	private List<String> privileges;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<String> privileges) {
		this.privileges = privileges;
	}
}
