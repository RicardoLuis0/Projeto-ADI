package com.ricardoluis.auth;

public class AuthKey {
	private String username;

	public AuthKey() {
		
	}

	public AuthKey(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
