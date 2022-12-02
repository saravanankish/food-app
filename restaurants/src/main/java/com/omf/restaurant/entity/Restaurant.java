package com.omf.restaurant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "t_restaurants")
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
