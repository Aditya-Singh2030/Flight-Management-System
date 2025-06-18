package com.Case_Study.Payment.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("BOOKINGS")
public interface BookingClient {
	
	@GetMapping("booking/Cost")
	public Double showTotalCost(@RequestParam long bookingId);

}
