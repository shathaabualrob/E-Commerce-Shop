package com.ejada.task2wallet.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="shop-service")
public interface ShopProxy {
	@PostMapping("/shopping-cart/{userId}")
	public String createShoppingCartForUser(@PathVariable Integer userId);
}