package com.ejada.task2shop.shoppingCart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
	ShoppingCart getShoppingCartByUserId(Integer user_id);
}
