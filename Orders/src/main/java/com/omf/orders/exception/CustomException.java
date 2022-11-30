package com.omf.orders.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;
	private int statusCode;

	public CustomException(String message, int statusCode) {
		super(message);
		this.message = message;
		this.statusCode = statusCode;
	}

}
