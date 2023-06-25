package com.ejada.task2shop.dto;

import java.util.Date;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public class CreateOrderDto {
	
	@NotNull
	private Integer userId;
	
	@FutureOrPresent
	private Date orderCreationDate;
	
	public CreateOrderDto() {
		// TODO Auto-generated constructor stub
	}

	public CreateOrderDto(Integer userId, Date orderCreationDate) {
		super();
		this.userId = userId;
		this.orderCreationDate = orderCreationDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getOrderCreationDate() {
		return orderCreationDate;
	}

	public void setOrderCreationDate(Date orderCreationDate) {
		this.orderCreationDate = orderCreationDate;
	}

	@Override
	public String toString() {
		return "OrderDto [userId=" + userId + 
				", orderCreationDate=" + orderCreationDate
				+ "]";
	}
	
	
}
