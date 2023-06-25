package com.ejada.task2shop.order;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_product")
//@IdClass(Order_Product_Id.class)
public class OrderProduct {
	
	@EmbeddedId
	private OrderProductId orderProductId;
	
	@ManyToOne
	@JoinColumn(name = "orderId", insertable = false, updatable = false)	
	private Order order;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "unit_price")
	private Float unitPrice;
	
	@Column(name = "total_price")
	private Float totalPrice;

	public OrderProduct() {
	
	}
	
	public OrderProduct(OrderProductId orderProductId, 
			Order order, Integer quantity, Float unitPrice,
			Float totalPrice) {
		super();
		this.orderProductId = orderProductId;
		this.order = order;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.totalPrice = totalPrice;
	}

	
	public OrderProductId getOrderProductId() {
		return orderProductId;
	}

	public void setOrderProductId(OrderProductId orderProductId) {
		this.orderProductId = orderProductId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}


	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Float getTotal_price() {
		return totalPrice;
	}

	public void setTotal_price(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Order_Product [orderProductId=" + orderProductId + ", order=" + order + ", quantity=" + quantity
				+ ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice + "]";
	}
	
	
}
