package com.ejada.task2shop.shoppingCart;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class ShoppingCartProductId implements Serializable {
	
	@Column(name = "shoppingCartId")
	private Integer shoppingCartId;
	
	@Column(name = "productId")
	private Integer productId;
	
	public ShoppingCartProductId() {
		// TODO Auto-generated constructor stub
	}

	public ShoppingCartProductId(Integer shoppingCartId, Integer productId) {
		super();
		this.shoppingCartId = shoppingCartId;
		this.productId = productId;
	}

	public Integer getShoppingCart_id() {
		return shoppingCartId;
	}

	public void setShoppingCart_id(Integer shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public Integer getProduct_id() {
		return productId;
	}

	public void setProduct_id(Integer productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "Order_Product_Id [shoppingCartId=" + shoppingCartId + ", productId=" + productId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, shoppingCartId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCartProductId other = (ShoppingCartProductId) obj;
		return Objects.equals(productId, other.productId) && Objects.equals(shoppingCartId, other.shoppingCartId);
	}

	
	
	
	
}
