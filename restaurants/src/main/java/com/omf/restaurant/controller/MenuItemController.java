package com.omf.restaurant.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.omf.restaurant.entity.MenuItem;
import com.omf.restaurant.service.CrudService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/menu")
@Validated
@Slf4j
public class MenuItemController {

	@Autowired
	private CrudService<MenuItem> menuService;

	@GetMapping
	public ResponseEntity<List<MenuItem>> getAllMenuItem() {
		log.info("REQUEST: get all menu items");
		return ResponseEntity.ok(menuService.getAll(null));
	}

	@GetMapping("/{id}")
	public ResponseEntity<MenuItem> getMenuItemById(@PathVariable("id") String menuId) {
		log.info("REQUEST: get menu item by id {}", menuId);
		return ResponseEntity.ok(menuService.getById(menuId));
	}

	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<List<MenuItem>> getMenuItemOfRestaurant(@PathVariable("restaurantId") String restaurantId) {
		log.info("REQUEST: get menu items by restaurant id {}", restaurantId);
		Map<String, Object> query = new HashMap<>();
		query.put("restaurantId", restaurantId);
		return ResponseEntity.ok(menuService.getAll(query));
	}

	@PostMapping
	public ResponseEntity<String> addMenuItem(@RequestBody @Valid MenuItem menuItem) {
		log.info("REQUEST: create menu item data: {}", menuItem);
		return ResponseEntity.status(201).body(menuService.create(menuItem));
	}

	@PostMapping("/")
	public ResponseEntity<String> addMenuItem(@RequestBody @Valid List<MenuItem> menuItem) {
		log.info("REQUEST: create list menu item size {}", menuItem.size());
		return ResponseEntity.status(201).body(menuService.createAll(menuItem));
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateMenuItem(@PathVariable("id") String menuId,
			@RequestBody @Valid MenuItem menuItem) {
		log.info("REQUEST: update menu item with id {}, data {}", menuId, menuItem);
		return ResponseEntity.status(201).body(menuService.update(menuId, menuItem));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteMenuItem(@PathVariable("id") String menuId) {
		log.info("REQUEST: delete list menu item with id {}", menuId);
		menuService.delete(menuId);
		return ResponseEntity.status(204).build();
	}

}
