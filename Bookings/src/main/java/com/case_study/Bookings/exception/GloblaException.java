package com.case_study.Bookings.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GloblaException {
	@ExceptionHandler(BookingNotFound.class)
	public ResponseEntity<Map<String,Object>> FlightNotFoundException(BookingNotFound ex){
		Map<String,Object> error = new HashMap<>();
		
		error.put("time", LocalDateTime.now());
		error.put("message", ex.getMessage());
		error.put("Status", HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Map<String,Object>>(error,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(NoSeats.class)
	public ResponseEntity<Map<String,Object>> NoSeatsException(NoSeats ex){
		Map<String,Object> error = new HashMap<>();
		
		error.put("time", LocalDateTime.now());
		error.put("message", ex.getMessage());
		error.put("Status", HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Map<String,Object>>(error,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(PassengerNotFound.class)
	public ResponseEntity<Map<String,Object>> SeatNotFoundException(PassengerNotFound ex){
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
