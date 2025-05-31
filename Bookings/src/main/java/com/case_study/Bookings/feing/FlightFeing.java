package com.case_study.Bookings.feing;

import java.time.LocalDate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="FLIGHTS")
public interface FlightFeing {
	
	@GetMapping("flight/prices")
	public Double showPrices(@RequestParam Long flightId,@RequestParam String seatClass);
	
	@GetMapping("flight/seats")
	public Integer showSeats(@RequestParam Long flightId);
	
	@GetMapping("flight/flightId")
	public long showFlightID(@RequestParam String source,@RequestParam String destination ,@RequestParam LocalDate departureDate);
	
	@PutMapping("flight/updateSeats")
	public void updateSeats(@RequestParam Long flightId,@RequestParam Integer updatedSeats);
	
	@GetMapping("flight/flightNo")
	public String showFlightNo(@RequestParam String source,@RequestParam String destination);
	
}
