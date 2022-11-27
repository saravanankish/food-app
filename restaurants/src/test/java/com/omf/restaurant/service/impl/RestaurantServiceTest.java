package com.omf.restaurant.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.omf.restaurant.exception.CustomException;
import com.omf.restaurant.repository.RestaurantRepository;

@SpringBootTest
public class RestaurantServiceTest {

	@Mock
	private RestaurantRepository restaurantRepo;

	@InjectMocks
	private RestaurantService restaurantService;

	@Test
	public void testDelete() {
		Mockito.doNothing().when(restaurantRepo).deleteById(Mockito.anyString());
		Mockito.when(restaurantRepo.existsById("36t87y-22144-21314-1444")).thenReturn(true);

		restaurantService.delete("36t87y-22144-21314-1444");

		verify(restaurantRepo).deleteById(Mockito.anyString());
	}

	@Test
	public void testDelete_Exception() {
		Mockito.when(restaurantRepo.existsById("36t87y-22144-21314-1444")).thenReturn(false);

		assertThrows(CustomException.class, () -> restaurantService.delete("36t87y-22144-21314-1444"),
				"Restaurant with id 36t87y-22144-21314-1444 not found");
		verify(restaurantRepo, never()).deleteById("36t87y-22144-21314-1444");
	}

}
