package com.omf.restaurant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "menu")
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@ToString
public class MenuItem {

	@Id
	private String id;

	@Column(name = "restaurant_id")
	@NotEmpty(message = "Restaurant id cannot be empty")
	private String restaurantId;
	
	@Column(name = "item_name")
	@NotEmpty(message = "Item name cannot be empty")
	private String itemName;
	
	private String description;
	
	@Positive(message = "Item price must be greater than zero")
	private Long price;

}