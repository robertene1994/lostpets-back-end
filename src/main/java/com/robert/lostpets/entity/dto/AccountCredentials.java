package com.robert.lostpets.entity.dto;

import java.io.Serializable;

public class AccountCredentials implements Serializable {

	private static final long serialVersionUID = -3204502995106211166L;

	private String email;
	private String password;

	AccountCredentials() {

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
