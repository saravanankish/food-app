package com.omf.restaurant.utils;

import java.util.Map;

import org.springframework.util.StringUtils;

public class RestaurantUtils {

	public static boolean mapHasEmptyValues(Map<String, Object> map) {
		return map == null || map.values().stream().allMatch(obj -> obj == null || obj.equals("") || obj.equals(0F));
	}

	public static boolean mapHasValueForKey(Map<String, Object> map, String key) {
		return StringUtils.hasLength((String) map.get(key));
	}

	public static boolean mapHasValueForKeys(Map<String, Object> map, String key1, String key2) {
		return StringUtils.hasLength((String) map.get(key1)) && StringUtils.hasLength((String) map.get(key2));
	}

	public static boolean mapHasValueForKeys(Map<String, Object> map, String key1, String key2, String key3) {
		return StringUtils.hasLength((String) map.get(key1)) && StringUtils.hasLength((String) map.get(key2))
				&& StringUtils.hasLength((String) map.get(key3));
	}

}
