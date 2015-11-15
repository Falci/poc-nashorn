package me.falci.tcc.poc.forms;

import me.falci.tcc.poc.javascript.annotation.ValidateWith;

@ValidateWith("/js/client-and-server.js")
public class UserForm implements FormData {

	private String name;
	private String email;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
