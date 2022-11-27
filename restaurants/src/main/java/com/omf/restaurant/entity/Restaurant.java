package com.omf.restaurant.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "restaurants")
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
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
	
}
