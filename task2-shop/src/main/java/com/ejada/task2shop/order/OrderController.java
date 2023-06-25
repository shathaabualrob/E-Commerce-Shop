package com.ejada.task2shop.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejada.task2shop.dto.CreateOrderDto;
import com.ejada.task2shop.dto.ProductDto;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	private final OrderService orderService;
	
	@Autowired
	public OrderController(OrderService orderService) {
	    this.orderService = orderService;
	}
	
	// GET: get all orders
	@GetMapping("all")
	public List<Order> getAllOrders(){
		return orderService.getAllOrders();
	}
	
	// GET: get order by orderId
	@GetMapping("/{order_id}")
	public Order getOrderById(@PathVariable Integer order_id) {
		return orderService.getOrderById(order_id);
	}
	
	// GET: get all orders for a userId
	@GetMapping("/get-orders-for-user/{userId}")
	public Order getOrderByUserId(@PathVariable Integer userId) {
		return orderService.getOrderByUserId(userId);
	}
	
	// GET: get all products for an order
	@GetMapping("/get-products-in-order/{orderId}")
	public List<ProductDto> getAllProductsDetailsForOrder(@PathVariable Integer orderId){
		return orderService.getAllProductsDetailsForOrder(orderId);
	}
	
	// POST: create new order -> payment withdrawal after canceling with the order's total price
	@PostMapping("/create-order")
	public Order createOrder(@RequestBody CreateOrderDto order ) {
		return orderService.createOrder(order);
	}
	
	// POST: cancel order -> payment deposit after canceling with the order's total price
	@PostMapping("/cancle-order/{orderId}")
	public Order cancleOrder(@PathVariable Integer orderId) {
		return orderService.cancleOrder(orderId);
	}
	
}
