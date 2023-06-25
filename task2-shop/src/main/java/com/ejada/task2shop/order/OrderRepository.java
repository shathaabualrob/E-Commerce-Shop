package com.ejada.task2shop.order;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	public Order getByUserId(Integer userId);

	
//	@Override
//	public Optional<Order> findById(Integer id);
}
