package com.omf.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omf.restaurant.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, String> {

	public List<MenuItem> findByRestaurantId(String restaurantId);
	
}
