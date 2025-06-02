package com.case_study.Flights.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.case_study.Flights.entity.Flights;
import com.case_study.Flights.entity.Seat;

@Repository
public interface SeatDao extends JpaRepository<Seat, Long>{

//	Seat findByFlightIdAndSeatClass(long flightId, String seatClass);
	
//	@Query("SELECT s.price FROM Seat s WHERE s.flight = :flight AND s.seatClass = :seatClass")
//	Double findByFlightAndSeatClass(@Param("flight") Flights flight,@Param("seatClass") String seatClass);

	@Query("SELECT s.price FROM Seat s WHERE s.flights = :flights AND s.seatClass = :seatClass")
	Double findPriceByFlightsAndSeatClass(@Param("flights") Flights flights,@Param("seatClass") String seatClass);

}
