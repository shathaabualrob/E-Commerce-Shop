package com.ejada.task2shop.order;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_table")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "creation_date")
	private LocalDateTime creationDate;
	
	@Column(name = "total_amount")
	private Float totalAmount;
	
	@Column(name = "cancled")
	private Boolean cancled;
	
	
	public Order() {
		
	}

	public Order( 
			Integer userId, 
			LocalDateTime creationDate, 
			Float totalAmount,
			Boolean cancled) {
		
		this.userId = userId;
		this.creationDate = creationDate;
		this.totalAmount = totalAmount;
		this.cancled = cancled;
	}
	
	
	public Integer getId() {
		return id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUser_id(Integer userId) {
		this.userId = userId;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotal_amount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}
	

	public Boolean getCancled() {
		return cancled;
	}

	public void setCancled(Boolean cancled) {
		this.cancled = cancled;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", creationDate=" + creationDate + ", totalAmount="
				+ totalAmount + ", cancled=" + cancled + "]";
	}

	
	

}
