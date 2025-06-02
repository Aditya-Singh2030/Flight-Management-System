package com.case_study.Flights.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.case_study.Flights.entity.Flights;

@Repository
public interface FlightDao extends JpaRepository<Flights, Long>{

	List<Flights> findBySourceAndDestinationAndDepartureDate(String source, String destination,
			LocalDate departureDate);
	
	@Query("SELECT f.totalSeats FROM Flights f WHERE f.flightId = :flightId")
	Integer findTotalSeatsByFlightId(@Param("flightId") Long flightId);

	@Query("SELECT f.flightId FROM Flights f WHERE f.source = :source AND f.destination = :destination AND f.departureDate = :departureDate")
	long findFlightIdBySourceAndDestinationAndDepartureDate(@Param("source") String source,@Param("destination") String destination,@Param("departureDate") LocalDate departureDate);

	@Query("SELECT f.flightNo FROM Flights f WHERE f.source = :source AND f.destination = :destination")
	String findFlightNoBySourceAndDestination(@Param("source") String source,@Param("destination") String destination);

	Flights findFlightsByFlightNo(String flightNo);

}
