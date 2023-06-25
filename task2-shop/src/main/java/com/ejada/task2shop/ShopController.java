package com.ejada.task2shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ejada.task2shop.dto.ProductDto;
import com.ejada.task2shop.proxy.InventoryProxy;
//import com.ejada.task2shop.proxy.WalletProxy;

//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController("/shop")
public class ShopController {
	
	@Autowired
	private InventoryProxy inventoryProxy; 
//	@Autowired
//	private WalletProxy walletProxy; 
	
	@GetMapping("/get-all-products-in-inventory")
	public List<ProductDto> getAllProduct(){
		return inventoryProxy.getAllProducts();
	}

	@GetMapping("/get-product-by-id/{productId}")
	public ProductDto getProductById(@PathVariable Integer productId) {
		return inventoryProxy.getProductById(productId);
	}
	
	public String staticShopContent(Exception e){
		return "Fallback Static Shop";
	}
}
