package com.ejada.task2wallet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateUserDto {
	
	@Email
	@NotNull
	@NotBlank
	private String email;
	
	@NotBlank
	@NotNull
	private String username;
	
	@Pattern(regexp = "\"^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$\"\r\n")
	private String password;
	
	public CreateUserDto() {
		// TODO Auto-generated constructor stub
	}

	public CreateUserDto(String email, String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return "CreateUserDto [email=" + email + ", username=" + username + ", password=" + password + "]";
	}
	
	
}
