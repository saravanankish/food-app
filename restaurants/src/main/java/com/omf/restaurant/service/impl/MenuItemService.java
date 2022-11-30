package com.omf.restaurant.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omf.restaurant.entity.ItemResponse;
import com.omf.restaurant.entity.MenuItem;
import com.omf.restaurant.exception.CustomException;
import com.omf.restaurant.repository.MenuItemRepository;
import com.omf.restaurant.service.CrudService;
import com.omf.restaurant.utils.RestaurantUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MenuItemService implements CrudService<MenuItem> {

	@Autowired
	private MenuItemRepository menuRepo;

	@Override
	public List<MenuItem> getAll(Map<String, Object> queryParam) {
		if (RestaurantUtils.mapHasEmptyValues(queryParam)) {
			log.info("Returned all menu items");
			return menuRepo.findAll();
		} else {
			log.info("Returned menu items of restaurant id {}", queryParam.get("restaurantId"));
			return menuRepo.findByRestaurantId((String) queryParam.get("restaurantId"));
		}
	}

	@Override
	public MenuItem getById(String id) {
		return menuRepo.findById(id)
				.orElseThrow(() -> new CustomException(String.format("Menu item with id %s not found", id), 404));
	}

	@Override
	public String create(MenuItem data) {
		data.setId(UUID.randomUUID().toString());
		MenuItem savedMenu = menuRepo.saveAndFlush(data);
		log.info("Created restaurant {}", savedMenu);
		return "Added successfully";
	}

	@Override
	public String createAll(List<MenuItem> data) {
		data.stream().forEach(menu -> menu.setId(UUID.randomUUID().toString()));
		List<MenuItem> savedMenus = menuRepo.saveAll(data);
		log.info("Created restaurants count {}", savedMenus.size());
		return "Added successfully";
	}

	@Override
	public String update(String id, MenuItem data) {
		boolean existsInDb = menuRepo.existsById(id);
		if (!existsInDb) {
			throw new CustomException(String.format("Restaurant with id %s not found", id), 404);
		}
		MenuItem updatedMenu = menuRepo.save(data);
		log.info("Updated restaurants id: {}, data: {}", id, updatedMenu);
		return "Updated successfully";
	}

	@Override
	public void delete(String id) {
		boolean existsInDb = menuRepo.existsById(id);
		if (!existsInDb) {
			throw new CustomException(String.format("Restaurant with id %s not found", id), 404);
		}
		log.info("Deleted restaurant with id {}", id);
		menuRepo.deleteById(id);
	}

	public List<ItemResponse> getItems(List<String> itemIds) {
		List<ItemResponse> itemResponse = new ArrayList<ItemResponse>();

		itemIds.forEach(itemId -> {
			ItemResponse item = new ItemResponse();
			Optional<MenuItem> menuItemOpt = menuRepo.findById(itemId);
			item.setExists(menuItemOpt.isPresent());
			item.setItemId(itemId);
			if (menuItemOpt.isPresent()) {
				item.setPrice(menuItemOpt.get().getPrice());
			}
			itemResponse.add(item);
		});

		return itemResponse;
	}

}
