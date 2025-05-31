package com.project.check_in.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(BookingNotFound.class)
	public ResponseEntity<Map<String,Object>> BookingNotFoundException(BookingNotFound ex){
		Map<String,Object> error = new HashMap<>();
		
		error.put("time", LocalDateTime.now());
		error.put("message", ex.getMessage());
		error.put("Status", HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Map<String,Object>>(error,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(SeatNotFound.class)
	public ResponseEntity<Map<String,Object>> SeatNotFoundException(SeatNotFound ex){
		Map<String,Object> error = new HashMap<>();
		
		error.put("time", LocalDateTime.now());
		error.put("message", ex.getMessage());
		error.put("Status", HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Map<String,Object>>(error,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,Object>> Exceptions(Exception ex){
		Map<String,Object> error = new HashMap<>();
		
		error.put("time", LocalDateTime.now());
		error.put("message", ex.getMessage());
		error.put("Status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<Map<String,Object>>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
