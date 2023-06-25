package com.ejada.task2shop.shoppingCart;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShoppingCartProductRepository extends JpaRepository<ShoppingCartProduct, ShoppingCartProductId> {
	
	public List<ShoppingCartProduct> findByShoppingCartId(Integer shoppingCartId);
	
	@Query(value = "SELECT e FROM ShoppingCartProduct e WHERE e.shoppingCartProductId.shoppingCartId = :shoppingCartId AND e.shoppingCartProductId.productId = :productId")
	public ShoppingCartProduct findByShoppingCartProductId(Integer shoppingCartId, Integer productId);
	
	public Optional<ShoppingCartProduct> findById(ShoppingCartProductId id);
}
