package com.ejada.task2shop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ProductShoppingCartDto {
	
	@NotNull
	private Integer userId;
	
	@NotNull
	private Integer productId;
	
	@NotNull
	@Min(1)
	private Integer quantity;
	
	public ProductShoppingCartDto() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductShoppingCartDto(Integer userId, Integer productId, Integer quantity) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getProductId() {
		return productId;
	}
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "AddProductToShoppingCartDto [userId=" + userId + ", productId=" + productId + ", quantity=" + quantity
				+ "]";
	}
	
}
