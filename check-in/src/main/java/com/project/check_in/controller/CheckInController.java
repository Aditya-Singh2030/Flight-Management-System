package com.project.check_in.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.check_in.entity.CheckIn;
import com.project.check_in.exceptions.SeatNotFound;
import com.project.check_in.service.CheckInService;

@RestController
@RequestMapping("checked")
public class CheckInController {
	
	@Autowired
	private CheckInService service;
	
	private static final Logger log = LoggerFactory.getLogger(CheckInController.class);
	
//	@GetMapping("")
//	public String test() {
//		return "working";
//	}
	
	@GetMapping("showChecked")
	public List<CheckIn> show(){
		log.info("Total number of bookings are going ");
		return service.show();
	}
	
	
	@PostMapping("checkin")
	public CheckIn add(@RequestBody CheckIn check,@RequestParam long bookingId) throws SeatNotFound {
		log.info("Check in through boooking id");
		return service.add(check,bookingId);
	}
	
}
