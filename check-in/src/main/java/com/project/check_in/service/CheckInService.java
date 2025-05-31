package com.project.check_in.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.check_in.dao.CheckInDao;
import com.project.check_in.entity.Booking;
import com.project.check_in.entity.CheckIn;
import com.project.check_in.entity.Passenges;
import com.project.check_in.entity.SeatAssign;
import com.project.check_in.exceptions.SeatNotFound;
import com.project.check_in.feing.BookingFeing;
import com.project.check_in.feing.FlightFeing;

@Service
public class CheckInService {

	@Autowired
	private CheckInDao repo;
	
	@Autowired
	private BookingFeing booking;
	
	@Autowired
	private FlightFeing flight;
	
	
	public List<CheckIn> show() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}


	public CheckIn add(CheckIn check, long bookingId) throws SeatNotFound {
		// TODO Auto-generated method stub
		
		Booking bookings = booking.showById(bookingId);
		String seatClass = booking.showSeatClass(bookingId);
		
		List<Passenges> passengers = booking.showPassengesByBookingId(bookingId);
		
		
		check.setBookingId(bookingId);
		check.setFlightNo(bookings.getFlightNo());
		check.setCheckInTime(LocalDateTime.now());
		check.setStatus("Checked In");
		
		List<SeatAssign> seatAssignment = new ArrayList<>();
		Set<String> usedSeats = new HashSet<>();
		
		int start = 0 , end = 0;
		String surfix = "";
		
		switch(seatClass.toLowerCase()) {
		case "economy":
			start = 20;
			end = 80;
			surfix="E";
			break;
		case "business":
			start = 9;
			end = 19;
			surfix="B";
			break;
		case "first class":
			start = 1;
			end = 8;
			surfix="F";
			break;
		default:
			throw new SeatNotFound("No seats are available");
		}
		
		Random rand = new Random();
		
		for(Passenges passenger : passengers) {
			String seatNo;
			do {
				int seatNum = rand.nextInt((end-start)+1)+start;
				seatNo = seatNum + surfix;
			}while(usedSeats.contains(seatNo));
			
			
			usedSeats.add(seatNo);
			
			SeatAssign assign = new SeatAssign();
			
			assign.setName(passenger.getFullName());
			assign.setPassengerId(passenger.getPassengerId());
			assign.setSeatNo(seatNo);
			assign.setSeatStatus("Occupied");
			assign.setCheckIn(check);
			
			seatAssignment.add(assign);
		}
		
		check.setSeatAssign(seatAssignment);
		
		return repo.save(check);
	}



	

	

}
