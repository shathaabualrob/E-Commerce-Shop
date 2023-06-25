package com.ejada.task2shop.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejada.task2shop.dto.CreateOrderDto;
import com.ejada.task2shop.dto.ProductDto;
import com.ejada.task2shop.exception.EntityCreationException;
import com.ejada.task2shop.exception.FeignException;
import com.ejada.task2shop.exception.NotFoundException;
import com.ejada.task2shop.proxy.InventoryProxy;
import com.ejada.task2shop.proxy.WalletProxy;
import com.ejada.task2shop.shoppingCart.ShoppingCart;
import com.ejada.task2shop.shoppingCart.ShoppingCartProduct;
import com.ejada.task2shop.shoppingCart.ShoppingCartProductRepository;
import com.ejada.task2shop.shoppingCart.ShoppingCartRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final OrderProductRepository orderProductRepository;
	private final ShoppingCartRepository shoppingCartRepository;
	private final ShoppingCartProductRepository shoppingCartProductRepository;
	private final InventoryProxy inventoryProxy;
	private final WalletProxy walletProxy;
	
	
	@Autowired
	public OrderService(
			OrderRepository orderRepository,
			OrderProductRepository orderProductRepository,
			ShoppingCartRepository shoppingCartRepository,
			ShoppingCartProductRepository shoppingCartProductRepository,
			InventoryProxy inventoryProxy,
			WalletProxy walletProxy
			) {
	    this.orderRepository 				= orderRepository;
	    this.orderProductRepository 		= orderProductRepository;
	    this.shoppingCartRepository 		= shoppingCartRepository;
	    this.shoppingCartProductRepository 	= shoppingCartProductRepository;
	    this.inventoryProxy 				= inventoryProxy;
	    this.walletProxy 					= walletProxy;
	}
	
	public List<Order> getAllOrders() {
		try {
			return orderRepository.findAll();
		} catch (Exception e) {
			throw new NotFoundException("There are no orders found.");
		}
	}
	
	public Order getOrderById (Integer order_id) {
		try {
			return orderRepository.findById(order_id).orElse(null);
		} catch (Exception e) {
			throw new NotFoundException("The order cannot be found.");
		}
	}

	public Order getOrderByUserId(Integer userId) {
		try {
			return orderRepository.getByUserId(userId);
		} catch (Exception e) {
			throw new NotFoundException("The order cannot be found.");
		}
	}
	
	public List<ShoppingCartProduct> getShoppingCartProducts(Integer userId){
		ShoppingCart shoppingCart;
		try {shoppingCart = shoppingCartRepository.getShoppingCartByUserId(userId);} 
		catch (Exception e) {
			throw new NotFoundException("This user has no shopping cart.");
		}
		try {return shoppingCartProductRepository.findByShoppingCartId(shoppingCart.getId());} 
		catch (Exception e) {
			throw new NotFoundException("No products where found in this shopping cart.");
		}
	}
	
	public List<ProductDto> getProductsInShoppingCart(Integer userId){
		List<ShoppingCartProduct> shoppingCartProducts = getShoppingCartProducts(userId);
		
		List<Integer> productsIds = new ArrayList<Integer>();
		for (ShoppingCartProduct shoppingCartProduct : shoppingCartProducts)
			productsIds.add(shoppingCartProduct.getShoppingCartProductId().getProduct_id());
		
		try {
			return inventoryProxy.getProductsForListOfIds(productsIds);
		} catch (Exception e) {
			throw new FeignException("An error occurred while trying to call Inventory service form the Shop service");
		}
	}

	public List<ProductDto> getAllProductsDetailsForOrder(Integer orderId) {
		Order order = getOrderById(orderId);
		return getProductsInShoppingCart(order.getUserId());
	}
	
	@Transactional
	public Order createOrder(CreateOrderDto order) {
		List<ProductDto> productsInUserShoppingCart = getProductsInShoppingCart(order.getUserId());
		
		ShoppingCart shoppingCart;
		try {shoppingCart = shoppingCartRepository.getShoppingCartByUserId(order.getUserId());} 
		catch (Exception e) {
			throw new NotFoundException("Can't find a shopping cart for this user.");
		}
		List<ShoppingCartProduct> shoppingCartProducts = getShoppingCartProducts(order.getUserId());
		if(productsInUserShoppingCart.size() > 0) {
			Order createdOrder;
			try {
				createdOrder = orderRepository.save(new Order(
					order.getUserId(),
					LocalDateTime.now(),
					shoppingCart.getTotalPrice(),
					false
				));
			} catch (Exception e) {
				throw new EntityCreationException("Something went wrong while trying to create your order.");
			}
			
			for(ShoppingCartProduct product : shoppingCartProducts) {
				try {
					orderProductRepository.save(new OrderProduct(
						new OrderProductId(
							createdOrder.getId(),
							product.getShoppingCartProductId().getProduct_id()
						),
						createdOrder,
						product.getQuantity(),
						product.getUnit_price(),
						product.getTotal_price()
					));
				} catch (Exception e) {
					throw new EntityCreationException("Something went wrong while trying to create your order.");
				}
			}
			
			try {
				walletProxy.withdraw(order.getUserId(), shoppingCart.getTotalPrice());
			} catch (Exception e) {
				throw new FeignException("Error occurred when trying to call the Wallet service from the Order service.");
			}

			return createdOrder;
		}else {
			throw new EntityCreationException("There are no products inside the shopping cart.");
		}
	}

	public Order cancleOrder(Integer orderId) {
		Order order = getOrderById(orderId);
		order.setCancled(true);
		order = orderRepository.save(order);
		ShoppingCart shoppingCart;
		try {shoppingCart = shoppingCartRepository.getShoppingCartByUserId(order.getUserId());} 
		catch (Exception e) {
			throw new NotFoundException("Can't find a shopping cart for this user.");
		}
		try {
			walletProxy.withdraw(order.getUserId(), shoppingCart.getTotalPrice());
		} catch (Exception e) {
			throw new FeignException("Error occurred when trying to call the Wallet service from the Order service.");
		}
		return null;
	}
	
	
	
}

