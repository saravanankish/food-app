package com.omf.restaurant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "t_restaurants")
@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamicInsert
@DynamicUpdate
public class Restaurant {

	@Id
	private String id;

	@Column(name = "restaurant_name")
	@NotBlank(message = "Restaurant name cannot be empty")
	private String restaurantName;

	@NotBlank(message = "Restaurant city cannot be empty")
	private String city;

	private String street;

	private String state;

	private String owner;

	@NotBlank(message = "Restaurant cuisine cannot be empty")
	private String cuisine;

	private String pincode;

	public Restaurant() {
		super();
	}

	public Restaurant(String id, @NotBlank(message = "Restaurant name cannot be empty") String restaurantName,
			@NotBlank(message = "Restaurant city cannot be empty") String city, String street, String state,
			String owner, @NotBlank(message = "Restaurant cuisine cannot be empty") String cuisine, String pincode) {
		super();
		this.id = id;
		this.restaurantName = restaurantName;
		this.city = city;
		this.street = street;
		this.state = state;
		this.owner = owner;
		this.cuisine = cuisine;
		this.pincode = pincode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
