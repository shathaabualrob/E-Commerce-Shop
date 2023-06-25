package com.ejada.task2shop.shoppingCart;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "shopping_cart_product")
public class ShoppingCartProduct {
	
	@EmbeddedId
	private ShoppingCartProductId shoppingCartProductId;
	
	@ManyToOne
    @JoinColumn(name = "shoppingCartId", insertable = false, updatable = false)	
	private ShoppingCart shoppingCart;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "unit_price")
	private Float unit_price;
	
	@Column(name = "total_price")
	private Float total_price;

	public ShoppingCartProduct() {
		
	}
	
	

	public ShoppingCartProduct(ShoppingCartProductId shoppingCartProductId, ShoppingCart shoppingCart,
			Integer quantity, Float unit_price, Float total_price) {
		super();
		this.shoppingCartProductId = shoppingCartProductId;
		this.shoppingCart = shoppingCart;
		this.quantity = quantity;
		this.unit_price = unit_price;
		this.total_price = total_price;
	}



	public ShoppingCartProductId getShoppingCartProductId() {
		return shoppingCartProductId;
	}

	public void setShoppingCartProductId(ShoppingCartProductId shoppingCartProductId) {
		this.shoppingCartProductId = shoppingCartProductId;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Float unit_price) {
		this.unit_price = unit_price;
	}

	public Float getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Float total_price) {
		this.total_price = total_price;
	}

	@Override
	public String toString() {
		return "ShoppinCart_Product [shoppingCartProductId=" + shoppingCartProductId + ", "
				+ "shoppingCart=" + shoppingCart
				+ ", quantity=" + quantity + 
				", unit_price=" + unit_price + 
				", total_price=" + total_price + "]";
	}

}
