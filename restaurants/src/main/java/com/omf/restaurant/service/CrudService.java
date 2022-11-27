package com.omf.restaurant.service;

import java.util.List;
import java.util.Map;

public interface CrudService<T> {
	
	public List<T> getAll(Map<String, Object> queryParam);
	
	public T getById(String id);
	
	public String create(T data);
	
	public String createAll(List<T> data);
	
	public String update(String id, T data);
	
	public void delete(String id);
}
