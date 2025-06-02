package com.project.check_in.feing;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.check_in.entity.Booking;
import com.project.check_in.entity.Passenges;

@FeignClient(name="BOOKINGS")
public interface BookingFeing {
	
	@GetMapping("booking/id")
	public Booking showById(@RequestParam long bookingId);
	
	@GetMapping("booking/passengers")
	public List<Passenges> showPassengesByBookingId(@RequestParam long bookingId);
	
	@GetMapping("booking/seatClass")
	public String showSeatClass(@RequestParam long bookingId);

}
