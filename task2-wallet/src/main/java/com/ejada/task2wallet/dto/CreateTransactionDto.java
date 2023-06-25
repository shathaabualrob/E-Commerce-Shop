package com.ejada.task2wallet.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateTransactionDto {
	
	@NotNull
	@Size(min = 6, max = 20)
	private Float amount;
	
	@NotNull
	@FutureOrPresent
	private LocalDateTime date;
	
	public CreateTransactionDto() {
		// TODO Auto-generated constructor stub
	}
	public CreateTransactionDto(Float amount, LocalDateTime date) {
		super();
		this.amount = amount;
		this.date = date;
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
	@Override
	public String toString() {
		return "CreateTransactionDto [amount=" + amount + ", date=" + date + "]";
	}
	
	
}
