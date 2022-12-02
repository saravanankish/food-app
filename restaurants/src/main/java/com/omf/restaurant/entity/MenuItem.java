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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "t_menu")
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@ToString
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

}
