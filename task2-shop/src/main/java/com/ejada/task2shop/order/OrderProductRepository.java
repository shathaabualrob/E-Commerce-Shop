package com.ejada.task2shop.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {
//	public List<OrderProduct> getByOrderId (Integer orderId);
	 @Query("SELECT o FROM OrderProduct o WHERE o.orderProductId.orderId = :orderId")
	 public List<OrderProduct> findByOrderId(@Param("orderId") Integer orderId);
}
