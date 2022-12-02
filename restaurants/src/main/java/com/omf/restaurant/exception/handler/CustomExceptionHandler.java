package com.omf.restaurant.exception.handler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.omf.restaurant.exception.CustomException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Map<String, Object>> handleCustomException(CustomException exp) {
		log.warn("Custom exception thrown, {}", exp.getMessage());
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("message", exp.getMessage());
		errorResponse.put("status", exp.getStatusCode());
		errorResponse.put("time", Instant.now().toString());
		return ResponseEntity.status(exp.getStatusCode()).body(errorResponse);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleDataException(MethodArgumentNotValidException exp) {
		Map<String, Object> errorResponse = new HashMap<>();
		Map<String, Object> errors = new TreeMap<>();
		exp.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
		log.error("Invalid data received, {}", errors);
		errorResponse.put("message", "Invalid request");
		errorResponse.put("status", 400);
		errorResponse.put("time", Instant.now().toString());
		errorResponse.put("fields", errors);
		return ResponseEntity.status(400).body(errorResponse);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, Object>> handleDataException(ConstraintViolationException exp) {
		Map<String, Object> errorResponse = new HashMap<>();
		Map<String, Object> errors = new TreeMap<>();
		Set<ConstraintViolation<?>> violations = exp.getConstraintViolations();
	    for (ConstraintViolation<?> violation : violations) {
	    	errors.put(violation.getPropertyPath().toString(), violation.getMessage());
	    }
		errorResponse.put("message", "Invalid request");
		errorResponse.put("status", 400);
		errorResponse.put("time", Instant.now().toString());
		errorResponse.put("fields", errors);
		log.error("Invalid data received, {}", errors);
		return ResponseEntity.status(400).body(errorResponse);
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<Map<String, Object>> handleSQLException(SQLIntegrityConstraintViolationException exp) {
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("message", "Invalid SQL syntax");
		errorResponse.put("status", 500);
		errorResponse.put("time", Instant.now().toString());
		log.error("Invalid SQL syntax, {}", exp.getMessage());
		return ResponseEntity.status(500).body(errorResponse);
	}
	
}
