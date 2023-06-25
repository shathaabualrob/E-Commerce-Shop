package com.ejada.task2wallet.transaction;

import java.time.LocalDateTime;

import com.ejada.task2wallet.wallet.Wallet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Float amount;
	private LocalDateTime date;
	
	@ManyToOne
	@JoinColumn(name = "walletId")
	private Wallet wallet;
	
	public Transaction() {
		
	}

	

	public Transaction(Float amount, LocalDateTime date, Wallet wallet) {
		super();
		this.amount = amount;
		this.date = date;
		this.wallet = wallet;
	}



	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", date=" + date + ", wallet=" + wallet + "]";
	}
	
	
	
}
