package com.ejada.task2shop.shoppingCart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejada.task2shop.dto.ProductShoppingCartDto;

import com.ejada.task2shop.dto.ProductDto;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
	
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	public ShoppingCartController(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}
	
	
	// -> Retrieve all products in a shopping cart
	@GetMapping("/get-all-products-in-shoppin-cart/{userId}")
	public List<ProductDto> getAllProductsInUserShoppinCart(@PathVariable Integer userId) {
		return shoppingCartService.getAllProductsInUserShoppinCart(userId);
	}
	
	// -> Retrieve a specific shopping cart by ID
	@GetMapping("/get-shopping-cart/{shoppingCartId}")
	public ShoppingCart getShoppingCartById(@PathVariable Integer shoppingCartId) {
		return shoppingCartService.getShoppingCartById(shoppingCartId);
	}
	
	// -> Default user initiation
	@PostMapping("/{userId}")
	public ShoppingCart createShoppingCartForUser(@PathVariable Integer userId) {
		return shoppingCartService.createShoppingCartForUser(userId);
	}
	
	// -> Add a product to a shopping cart
	@PostMapping("/add-product-to-shopping-cart")
	public ShoppingCartProduct addProductToShoppingCart(@RequestBody ProductShoppingCartDto body) {
		return shoppingCartService.addProductToShoppingCart(body.getUserId(), body.getProductId(), body.getQuantity());
	}
	
	// -> Increase/Decrease product quantity in shopping cart
	@PutMapping("/update-quantity")
	public ShoppingCartProduct updateQuantity(@RequestBody ProductShoppingCartDto body) {
		return shoppingCartService.updateQuantity(body.getUserId(), body.getProductId(), body.getQuantity());
	}
	
	// -> Remove a product from a shopping cart
	@DeleteMapping("/remove-item-from-shoppin-cart/{userId}/{productId}")
	public String removeItemFromShoppinCart(@PathVariable Integer userId, @PathVariable Integer productId) {
		return shoppingCartService.removeItemFromShoppinCart(userId, productId);
	}
	
	
}
