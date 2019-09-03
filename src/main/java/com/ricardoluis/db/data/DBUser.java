package com.ricardoluis.db.data;

public class DBUser {

	private String name;
	private String birthdate;
	
	public DBUser(String name, String birthdate) {
		this.name = name;
		this.birthdate = birthdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	
}
