package com.omf.orders.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.omf.orders.entity.Order;
import com.omf.orders.entity.OrderLineItem;
import com.omf.orders.exception.CustomException;
import com.omf.orders.model.ItemResponse;
import com.omf.orders.model.OrderStatus;
import com.omf.orders.repository.OrderRepository;

@Service
public class OrderService {
	
	public final Logger log = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RestTemplate restTemplate;

	public List<Order> getAll() {
		return orderRepository.findAll();
	}

	public Order getById(String orderId) {
		Optional<Order> orderOpt = orderRepository.findById(orderId);
		if (orderOpt.isEmpty()) {
			throw new CustomException("Order with order id " + orderId + " not found", 404);
		}
		return orderOpt.get();
	}

	public String updateOrderStatus(String orderId, String orderStatus) {
		Optional<Order> orderOptional = orderRepository.findById(orderId);
		if (orderOptional.isEmpty()) {
			throw new CustomException("Order with order id " + orderId + " not found", 404);
		}
		OrderStatus status;
		try {
			status = OrderStatus.valueOf(orderStatus);
		} catch (IllegalArgumentException e) {
			throw new CustomException("Order status is not valid", 400);
		}
		Order order = orderOptional.get();
		order.setOrderStatus(status);
		orderRepository.save(order);
		return "Order status updated successfully";
	}

	public String placeOrder(Order order) {
		order.setOrderId(UUID.randomUUID().toString());
		Map<String, Integer> itemIds = order.getOrderLineItems().stream()
				.collect(Collectors.toMap(OrderLineItem::getItemId, OrderLineItem::getQuantity));

		try {
			ItemResponse[] itemResponse = restTemplate.postForObject("lb://RESTAURANTS/menu/items",
					itemIds.keySet(), ItemResponse[].class);
			boolean allItemsExists = Arrays.stream(itemResponse).allMatch(ItemResponse::isExists);

			if (allItemsExists) {
				double totalPrice = 0;
				for (ItemResponse item : itemResponse) {
					totalPrice += (itemIds.get(item.getItemId()) * item.getPrice());
				}
				order.setTotalPrice(totalPrice);
				order.setOrderStatus(OrderStatus.PLACED);
				orderRepository.save(order);
				return "Order Placed Successfully";
			} else {
				throw new IllegalArgumentException("Product is not in stock, please try again later");
			}
		} catch (Exception e) {
			log.error("Exception occured while getting items, {}", e.getMessage());
			return "Failed to place order";
		}
	}

}
