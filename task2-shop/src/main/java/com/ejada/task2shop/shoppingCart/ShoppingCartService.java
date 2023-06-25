package com.ejada.task2shop.shoppingCart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejada.task2shop.dto.ProductDto;
import com.ejada.task2shop.exception.EntityCreationException;
import com.ejada.task2shop.exception.EntityDeletionException;
import com.ejada.task2shop.exception.FeignException;
import com.ejada.task2shop.exception.NotFoundException;
import com.ejada.task2shop.proxy.InventoryProxy;

@Service
public class ShoppingCartService {
	
	private final ShoppingCartRepository shoppingCartRepository;
	private final ShoppingCartProductRepository shoppingCartProductRepository;
	private final InventoryProxy inventoryProxy;
	
	@Autowired
	public ShoppingCartService(
			ShoppingCartRepository shoppingCartRepository,
			ShoppingCartProductRepository shoppingCartProductRepository,
			InventoryProxy inventoryProxy
			) {
		this.shoppingCartRepository 		= shoppingCartRepository;
		this.shoppingCartProductRepository 	= shoppingCartProductRepository;
		this.inventoryProxy 				= inventoryProxy;
	}
	
	public List<ShoppingCart> getAllProductsInShoppingCart(Integer userId){ return null;}
	
	public ShoppingCart createShoppingCartForUser(Integer userId) {
		try {
			return shoppingCartRepository.save(new ShoppingCart(userId,(float) 0.0));
		} catch (Exception e) {
			throw new EntityCreationException("Something went wrong while creating this user's shopping cart.");
		}
		
	}
	
	public ShoppingCart getShoppingCartByUserId(Integer userId) {
		try {
			return shoppingCartRepository.getShoppingCartByUserId(userId);			
		} catch (Exception e) {
			throw new NotFoundException("Shopping cart was not found");
		}
	}
	
	public ShoppingCart getShoppingCartById(Integer shoppingCartId) {
		try {
			return shoppingCartRepository.findById(shoppingCartId).get();
		} catch (Exception e) {
			throw new NotFoundException("Shopping cart was not found");
		}
	}

	public List<ProductDto> getAllProductsInUserShoppinCart(Integer userId) {
		
		ShoppingCart userShoppingCart = getShoppingCartByUserId(userId);
		
		List<ShoppingCartProduct> productsInShoppingCart ;
		try {
			productsInShoppingCart = shoppingCartProductRepository.findByShoppingCartId(userShoppingCart.getId());
		} catch (Exception e) {
			throw new NotFoundException("Something went wrogn while trying to fetch user's item from shopping cart.");
		}
		
		List<Integer> productsIds = new ArrayList<>();
		for (ShoppingCartProduct product : productsInShoppingCart)
			productsIds.add(product.getShoppingCartProductId().getProduct_id());
		
		List<ProductDto> productsInUserSHoppingList;
		try {			
			productsInUserSHoppingList = inventoryProxy.getProductsForListOfIds(productsIds);
		} catch (Exception e) {
			throw new FeignException("Error occurred while trying to call the inventory service insid shopping cart service.");
		}
		
		return productsInUserSHoppingList;
	}
	
	public ShoppingCartProduct addProductToShoppingCart(
		Integer userId, 
		Integer productId,
		Integer quantity) 
	{
		ProductDto product;
		try {product = inventoryProxy.getProductById(productId);} 
		catch (Exception e) {
			throw new FeignException("Error occurred while trying to call the inventory service insid shopping cart service.");
		}
		
		ShoppingCart userShoppingCart = getShoppingCartByUserId(userId);
		ShoppingCartProductId id = new ShoppingCartProductId(userShoppingCart.getId(),productId);
		return shoppingCartProductRepository.save(new ShoppingCartProduct(
				id,
				userShoppingCart,
				quantity,
				product.getPrice(),
				quantity*product.getPrice()
				));
	}

	public ShoppingCartProduct updateQuantity(
			Integer userId,
			Integer productId,
			Integer quantity
			) 
	{
		ShoppingCart userShoppingCart = getShoppingCartByUserId(userId);
		ShoppingCartProductId id = new ShoppingCartProductId(userShoppingCart.getId(),productId);
		
		ShoppingCartProduct shoppingCartProduct;
		try {
			shoppingCartProduct = shoppingCartProductRepository.findById(id).get();
		} catch (Exception e) {
			throw new NotFoundException("Shopping cart has no products.");
		}
		
		shoppingCartProduct.setQuantity(quantity);
		return shoppingCartProductRepository.save(shoppingCartProduct);
	}
	
	public String removeItemFromShoppinCart(
			Integer userId,
			Integer productId) 
	{
		ShoppingCart userShoppingCart = getShoppingCartByUserId(userId);
		ShoppingCartProductId id = new ShoppingCartProductId(userShoppingCart.getId(),productId);
		try {
			shoppingCartProductRepository.deleteById(id);
			return "Item was deleted";
		} catch (Exception e) {
			throw new EntityDeletionException("An error occurred while trying to remove an item from the shopping cart.");
		}
	}
}