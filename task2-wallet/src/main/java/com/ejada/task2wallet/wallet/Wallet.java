package com.ejada.task2wallet.wallet;

import java.util.List;

import com.ejada.task2wallet.transaction.Transaction;
import com.ejada.task2wallet.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Wallet {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Float balance;
	
	@OneToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@OneToMany(mappedBy = "wallet")
	private List<Transaction> transactions;
	
	public Wallet() {
		
	}
	
	

	public Wallet(Float balance, User user, List<Transaction> transactions) {
		super();
		this.balance = balance;
		this.user = user;
		this.transactions = transactions;
	}



	public Float getBalance() {
		return balance;
	}



	public void setBalance(Float balance) {
		this.balance = balance;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public List<Transaction> getTransactions() {
		return transactions;
	}



	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}



	public Integer getId() {
		return id;
	}



	@Override
	public String toString() {
		return "Wallet [id=" + id + ", balance=" + balance + ", user=" + user + ", transactions=" + transactions + "]";
	}

	
	
}
