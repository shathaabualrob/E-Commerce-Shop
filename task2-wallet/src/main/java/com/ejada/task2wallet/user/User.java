package com.ejada.task2wallet.user;

import com.ejada.task2wallet.wallet.Wallet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Boolean isAdmin;
	private String email;
	private String username;
	private String password;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private	Wallet wallet;
	
	
	public User() {
		
	}

	public User(
			Boolean isAdmin, 
			String email, 
			String username, 
			String password) 
	{
		this.isAdmin = isAdmin;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
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

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + 
				", isAdmin=" + isAdmin + 
				", email=" + email + 
				", username=" + username + 
				", password=" + password + 
				"]";
	}
}