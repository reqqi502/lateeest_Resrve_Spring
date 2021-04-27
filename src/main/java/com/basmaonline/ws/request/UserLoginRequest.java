package com.basmaonline.ws.request;

public class UserLoginRequest {
	
	private String email;
	public UserLoginRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	private String password;
	
	
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
