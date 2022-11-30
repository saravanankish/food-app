package com.omf.orders.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.omf.orders.entity.Order;
import com.omf.orders.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Order> getAllOrder() {
		return orderService.getAll();
	}

	@GetMapping("/{orderId}")
	@ResponseStatus(HttpStatus.OK)
	public Order getOrderById(@PathVariable("orderId") String orderId) {
		return orderService.getById(orderId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CompletableFuture<String> placeOrder(@RequestBody @Valid Order orderRequest) {
		return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));
	}

	@PutMapping("/{orderId}/{orderStatus}")
	@ResponseStatus(HttpStatus.CREATED)
	public String updateOrderStatus(@PathVariable("orderId") String orderId,
			@PathVariable("orderStatus") String orderStatus) {
		return orderService.updateOrderStatus(orderId, orderStatus);
	}

}
