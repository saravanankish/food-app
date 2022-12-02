package com.omf.restaurant.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omf.restaurant.entity.Restaurant;
import com.omf.restaurant.service.CrudService;

@RestController
@RequestMapping("/restaurant")
@Validated
public class RestaurantController {
	
	private final Logger log = LoggerFactory.getLogger(MenuItemController.class);

	@Autowired
	CrudService<Restaurant> restaurantService;

	@GetMapping
	public ResponseEntity<List<Restaurant>> getAllRestaurants(
			@RequestParam(name = "location", required = false) String location,
			@RequestParam(name = "cuisine", required = false) String cuisine,
			@RequestParam(name = "name", required = false) String name) {
		log.info("REQUEST: Get all restuarant");
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("location", location);
		queryParams.put("cuisine", cuisine);
		queryParams.put("name", name);
		return ResponseEntity.ok(restaurantService.getAll(queryParams));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("id") String restaurantId) {
		log.info("REQUEST: Get restuarant by id {}", restaurantId);
		return ResponseEntity.ok(restaurantService.getById(restaurantId));
	}

	@PostMapping
	public ResponseEntity<String> addRestaurant(@RequestBody @Valid Restaurant restaurant) {
		log.info("REQUEST: Add restuarant {}", restaurant);
		return ResponseEntity.status(201).body(restaurantService.create(restaurant));
	}

	@PostMapping("/all")
	public ResponseEntity<String> addAllRestaurants(@RequestBody List<@Valid Restaurant> restaurants) {
		log.info("REQUEST: Add all restuarant count {}", restaurants.size());
		return ResponseEntity.status(201).body(restaurantService.createAll(restaurants));
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateRestaurant(@PathVariable("id") String id,
			@Valid @RequestBody Restaurant restaurant) {
		log.info("REQUEST: Update all restuarant with id {}, data {}", id, restaurant);
		return ResponseEntity.status(201).body(restaurantService.update(id, restaurant));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRestaurant(@PathVariable("id") String restaurantId) {
		log.info("REQUEST: Delete restuarant by id {}", restaurantId);
		restaurantService.delete(restaurantId);
		return ResponseEntity.status(204).build();
	}
}
