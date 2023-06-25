package com.ejada.task2wallet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class LoginUserDto {
	
	@NotBlank
	@NotNull
	private String username;
	
	@Pattern(regexp = "\"^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$\"\r\n")
	private String password;
	
	public LoginUserDto() {
		// TODO Auto-generated constructor stub
	}

	public LoginUserDto(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginUserDto [username=" + username + 
				", password=" + password + "]";
	}
	
	
}
