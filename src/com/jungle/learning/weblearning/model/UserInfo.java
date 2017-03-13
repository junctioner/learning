package com.jungle.learning.weblearning.model;

public class UserInfo {
	private String id;
	private String name;
	private String password;

	public UserInfo() {
	}

	public UserInfo(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
