package com.omf.orders.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.omf.orders.model.OrderStatus;

@Entity
@Table(name = "t_orders")
@DynamicInsert
@DynamicUpdate
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

	@Id
	private String orderId;

	private Double totalPrice;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private List<@Valid OrderLineItem> orderLineItems;

	public Order() {
		super();
	}

	public Order(String orderId, Double totalPrice, OrderStatus orderStatus,
			List<@Valid OrderLineItem> orderLineItems) {
		super();
		this.orderId = orderId;
		this.totalPrice = totalPrice;
		this.orderStatus = orderStatus;
		this.orderLineItems = orderLineItems;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<OrderLineItem> getOrderLineItems() {
		return orderLineItems;
	}

	public void setOrderLineItems(List<OrderLineItem> orderLineItems) {
		this.orderLineItems = orderLineItems;
	}
	
	

}
