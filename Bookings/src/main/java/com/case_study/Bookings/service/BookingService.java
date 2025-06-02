package com.case_study.Bookings.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.case_study.Bookings.controller.BookingController;
import com.case_study.Bookings.dao.BookingDao;
import com.case_study.Bookings.dao.PassengeDao;
import com.case_study.Bookings.entity.Booking;
import com.case_study.Bookings.entity.Passenges;
import com.case_study.Bookings.exception.BookingNotFound;
import com.case_study.Bookings.exception.NoSeats;
import com.case_study.Bookings.feing.FlightFeing;

@Service
public class BookingService {
	@Autowired
	private BookingDao repo;
	@Autowired
	private PassengeDao passRepo;
	
	@Autowired
	private FlightFeing flight;
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(BookingService.class);

	public List<Booking> show() {
		// TODO Auto-generated method stub
		log.info("This is retreving the booking delails");
		return repo.findAll();
	}

	public Booking add(Booking booking) throws NoSeats {
		// TODO Auto-generated method stub
		log.info("This is retreving the booking delails");
		Long flightId = flight.showFlightID(booking.getSource(), booking.getDestination(), booking.getDepartureDate());
		
		booking.setFlightNo(flight.showFlightNo(booking.getSource(), booking.getDestination()));
		
		Integer totalSeats = flight.showSeats(flightId);
		
		log.info("setting number of seats ");
		
		int economySeats = (int) (totalSeats*0.80);
		int businessSeats = (int) (totalSeats*0.15);
		int firstClassSeats = (int) (totalSeats*0.5);
		
		if(booking.getSeatClass().equalsIgnoreCase("economy") && booking.getNoOfPassengers()>economySeats|| 
			booking.getSeatClass().equalsIgnoreCase("business") && booking.getNoOfPassengers()>businessSeats || 
		    booking.getSeatClass().equalsIgnoreCase("first class") && booking.getNoOfPassengers()>firstClassSeats){
			
			throw new NoSeats("Not enough seats available for the selected class.");
		}
		
		Double totalCost = flight.showPrices(flightId, booking.getSeatClass());
		
		log.info("getting total cost ");
		
		booking.setTotalCost(totalCost*booking.getNoOfPassengers());
		booking.setBookedAt(LocalDateTime.now());
		
//		Booking addBooking = repo.save(booking);
		
		for (Passenges passenger : booking.getPassenger()) {
            passenger.setBooking(booking); 
        }
		
		Integer updatedSeats = totalSeats - booking.getNoOfPassengers();
		flight.updateSeats(flightId, updatedSeats);
		
		return repo.save(booking);
	}

	public Booking getById(long bookingId) throws BookingNotFound {
		// TODO Auto-generated method stub
		log.info("this is showing the booking details according booking id");
		return repo.findById(bookingId).orElseThrow(()-> new BookingNotFound("Booking of this id os not here"));
	}

	public void deleteById(long bookingId) {
		// TODO Auto-generated method stub
		log.info("this is deleting the booking details according booking id");
		repo.deleteById(bookingId);
	}

	public List<Passenges> showPassengesByBookingId(long bookingId) {
		// TODO Auto-generated method stub
		return passRepo.findByBooking_BookingId(bookingId);
	}

	public String showSeatClass(long bookingId) {
		// TODO Auto-generated method stub
		return repo.findseatClassByBookingId(bookingId);
	}

}
