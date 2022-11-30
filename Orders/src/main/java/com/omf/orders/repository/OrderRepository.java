package com.omf.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omf.orders.entity.Order;

public interface OrderRepository extends JpaRepository<Order, String> {

}
