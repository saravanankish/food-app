package com.omf.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omf.restaurant.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

	public List<Restaurant> findByCuisineContainingIgnoreCase(String cuisine);

	public List<Restaurant> findByCityContainingIgnoreCase(String city);

	public List<Restaurant> findByRestaurantNameContainingIgnoreCase(String restaurantName);

	public List<Restaurant> findByCuisineContainingIgnoreCaseAndCityContainingIgnoreCase(String cuisine, String city);

	public List<Restaurant> findByCityContainingIgnoreCaseAndRestaurantNameContainingIgnoreCase(String city,
			String restaurantName);

	public List<Restaurant> findByRestaurantNameContainingIgnoreCaseAndCuisineContainingIgnoreCase(String restaurantName, String cuisine);

	public List<Restaurant> findByCityContainingIgnoreCaseAndCuisineContainingIgnoreCaseAndRestaurantNameContainingIgnoreCase(String city, String cuisine,
			String restaurantName);
	
}
