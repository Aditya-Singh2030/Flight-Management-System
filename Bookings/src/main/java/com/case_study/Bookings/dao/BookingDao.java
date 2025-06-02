package com.case_study.Bookings.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.case_study.Bookings.entity.Booking;

@Repository
public interface BookingDao extends JpaRepository<Booking, Long>{

	
	@Query("SELECT b.seatClass FROM Booking b WHERE b.bookingId = :bookingId")
	String findseatClassByBookingId(@Param("bookingId") long bookingId);

}
