package com.case_study.Bookings.controller;

import java.util.List;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.case_study.Bookings.entity.Booking;
import com.case_study.Bookings.entity.Passenges;
import com.case_study.Bookings.exception.BookingNotFound;
import com.case_study.Bookings.exception.NoSeats;
import com.case_study.Bookings.service.BookingService;

@RestController
@RequestMapping("booking")
public class BookingController {
	
	@Autowired
	private BookingService service;
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(BookingController.class);
	
//	@GetMapping("")
//	public String test() {
//		return "working";
//	}
	@GetMapping("showBooking")
	public List<Booking> show() {
		log.info("This is retreving the booking delails");
		return service.show();
	}
	
	@PostMapping("add")
	public Booking add(@RequestBody Booking booking) throws NoSeats {
		log.info("this is add booking along with passenger details");
		return service.add(booking);
		
	}
	
	@GetMapping("id")
	public Booking showById(@RequestParam long bookingId) throws BookingNotFound {
		log.info("this is showing the booking details according booking id");
		return service.getById(bookingId);
	}
	
	@GetMapping("passengers")
	public List<Passenges> showPassengesByBookingId(@RequestParam long bookingId){
		return service.showPassengesByBookingId(bookingId);
	}
	
	@GetMapping("seatClass")
	public String showSeatClass(@RequestParam long bookingId) {
		return service.showSeatClass(bookingId);
	}
	
	@DeleteMapping("delete")
	public void deleteById(@RequestParam long bookingId) {
		log.info("this is deleting the booking details according booking id");
		service.deleteById(bookingId);
	}
}
