package com.omf.restaurant.entity;

public class ItemResponse {

	private String itemId;
	private boolean exists;
	private Double price;

	public ItemResponse() {
		super();
	}

	public ItemResponse(String itemId, boolean exists, Double price) {
		super();
		this.itemId = itemId;
		this.exists = exists;
		this.price = price;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
