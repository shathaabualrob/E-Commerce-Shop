package com.ejada.task2shop.order;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class OrderProductId implements Serializable {
	
	@Column(name = "orderId")
	private Integer orderId;
	
	@Column(name = "productId")
	private Integer productId;
	
	public OrderProductId() {
		// TODO Auto-generated constructor stub
	}

	public OrderProductId(Integer orderId, Integer productId) {
		super();
		this.orderId = orderId;
		this.productId = productId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "Order_Product_Id [orderId=" + orderId + ", productId=" + productId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderProductId other = (OrderProductId) obj;
		return Objects.equals(orderId, other.orderId) && Objects.equals(productId, other.productId);
	}
	
	
	
}
