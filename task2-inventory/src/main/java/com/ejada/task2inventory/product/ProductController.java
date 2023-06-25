package com.ejada.task2inventory.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	private ProductService productService;
		
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/all")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/get-product-by-id/{productId}")
	public Product getProductById(@PathVariable Integer productId) {
		return productService.getProductById(productId);
	}
	
	@GetMapping("/get-products-for-list-of-ids/{listOfProductIds}")
	public List<Product> getProductsForListOfIds(@PathVariable List<Integer> listOfProductIds){
		List<Product> productsForListOfIds = productService.getProductsForListOfIds(listOfProductIds);
		System.out.println("Product Controller: "+ productsForListOfIds);
		return productsForListOfIds;
	}
	
	@PostMapping
	public Product createProduct(@Valid @RequestBody ProductDto productDto) {
		
		return productService.createProduct(productDto);
	}
	
	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable  Integer id) {
		return productService.deleteProduct(id);
	}
}