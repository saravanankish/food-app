package com.omf.restaurant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "t_menu")
@DynamicInsert
@DynamicUpdate
@JsonInclude(JsonInclude.Include.NON_NULL)
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
	private Double price;

	public MenuItem() {
		super();
	}

	public MenuItem(String id, @NotEmpty(message = "Restaurant id cannot be empty") String restaurantId,
			@NotEmpty(message = "Item name cannot be empty") String itemName, String description,
			@Positive(message = "Item price must be greater than zero") Double price) {
		super();
		this.id = id;
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
