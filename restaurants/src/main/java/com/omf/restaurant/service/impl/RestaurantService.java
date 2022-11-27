package com.omf.restaurant.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omf.restaurant.entity.Restaurant;
import com.omf.restaurant.exception.CustomException;
import com.omf.restaurant.repository.RestaurantRepository;
import com.omf.restaurant.service.CrudService;
import com.omf.restaurant.utils.RestaurantUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestaurantService implements CrudService<Restaurant> {

	@Autowired
	private RestaurantRepository restaurantRepo;

	@Override
	public List<Restaurant> getAll(Map<String, Object> queryParam) {
		log.info("Returned all restaurant, parameters {}", queryParam);
		if (RestaurantUtils.mapHasEmptyValues(queryParam)) {
			System.out.println("none");
			return restaurantRepo.findAll();
		}

		// 0 - Location, 1 - Cuisine, 2 - Name
		List<String> values = getValuesFromMap(queryParam);

		if (RestaurantUtils.mapHasValueForKeys(queryParam, "location", "cuisine", "name")) {
			return restaurantRepo
					.findByCityContainingIgnoreCaseAndCuisineContainingIgnoreCaseAndRestaurantNameContainingIgnoreCase(
							values.get(0), values.get(1), values.get(2));
		}

		if (RestaurantUtils.mapHasValueForKeys(queryParam, "cuisine", "name")) {
			return restaurantRepo.findByRestaurantNameContainingIgnoreCaseAndCuisineContainingIgnoreCase(values.get(2),
					values.get(1));
		} else if (RestaurantUtils.mapHasValueForKeys(queryParam, "location", "name")) {
			return restaurantRepo.findByCityContainingIgnoreCaseAndRestaurantNameContainingIgnoreCase(values.get(0),
					values.get(1));
		} else if (RestaurantUtils.mapHasValueForKeys(queryParam, "cuisine", "location")) {
			return restaurantRepo.findByCuisineContainingIgnoreCaseAndCityContainingIgnoreCase(values.get(2),
					values.get(0));
		} else {
			if (RestaurantUtils.mapHasValueForKey(queryParam, "location")) {
				return restaurantRepo.findByCityContainingIgnoreCase(values.get(0));
			} else if (RestaurantUtils.mapHasValueForKey(queryParam, "cuisine")) {
				return restaurantRepo.findByCuisineContainingIgnoreCase(values.get(1));
			} else {
				return restaurantRepo.findByRestaurantNameContainingIgnoreCase(values.get(2));
			}
		}
	}

	@Override
	public Restaurant getById(String id) {
		return restaurantRepo.findById(id)
				.orElseThrow(() -> new CustomException(String.format("Restaurant with id %s not found", id), 404));
	}

	@Override
	public String create(Restaurant data) {
		data.setId(UUID.randomUUID().toString());
		Restaurant savedRestaurant = restaurantRepo.saveAndFlush(data);
		log.info("Created restaurant {}", savedRestaurant);
		return "Added successfully";
	}

	@Override
	public String createAll(List<Restaurant> data) {
		data.stream().forEach(restaurant -> restaurant.setId(UUID.randomUUID().toString()));
		List<Restaurant> savedRestaurants = restaurantRepo.saveAll(data);
		log.info("Created restaurants count {}", savedRestaurants.size());
		return "Added successfully";
	}

	@Override
	public String update(String id, Restaurant data) {
		boolean existsInDb = restaurantRepo.existsById(id);
		if (!existsInDb) {
			throw new CustomException(String.format("Restaurant with id %s not found", id), 404);
		}
		Restaurant updatedRestaurant = restaurantRepo.save(data);
		log.info("Updated restaurants id: {}, data: {}", id, updatedRestaurant);
		return "Updated successfully";
	}

	@Override
	public void delete(String id) {
		boolean existsInDb = restaurantRepo.existsById(id);
		if (!existsInDb) {
			throw new CustomException(String.format("Restaurant with id %s not found", id), 404);
		}
		log.info("Deleted restaurant with id {}", id);
		restaurantRepo.deleteById(id);
	}

	public List<String> getValuesFromMap(Map<String, Object> map) {
		List<String> values = new ArrayList<>();
		values.add(map.get("location") != null ? (String) map.get("location") : null);
		values.add(map.get("cuisine") != null ? (String) map.get("cuisine") : null);
		values.add(map.get("name") != null ? (String) map.get("name") : null);

		return values;
	}

}
