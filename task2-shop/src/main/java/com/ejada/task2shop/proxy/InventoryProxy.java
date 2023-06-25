package com.ejada.task2shop.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.ejada.task2shop.dto.ProductDto;

@FeignClient(name="inventory-service")
public interface InventoryProxy {
	@GetMapping("/product/all")
	public List<ProductDto> getAllProducts();
	@GetMapping("/product/get-product-by-id/{productId}")
	public ProductDto getProductById(@PathVariable Integer productId);
	@GetMapping("/product/get-products-for-list-of-ids/{listOfProductIds}")
	public List<ProductDto> getProductsForListOfIds(@PathVariable List<Integer> listOfProductIds);
	
}
