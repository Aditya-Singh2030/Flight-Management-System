package com.case_study.Flights.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.case_study.Flights.entity.Flights;
import com.case_study.Flights.entity.Seat;
import com.case_study.Flights.exceptions.FlightNotFound;
import com.case_study.Flights.exceptions.SeatNotFound;
import com.case_study.Flights.service.FlightService;

@RestController
@RequestMapping("flight")
public class FlightController {
	
	@Autowired
	private FlightService service;
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(FlightController.class);
	
	
//	@GetMapping("")
//	public String test() {
//		return "working";
//	}
	@GetMapping("showFlight")
	public List<Flights> show(){
		log.info("it is showing all the flights that are in the data base");
		return service.show();
		
	}
	
	@GetMapping("showFlight/{flightId}")
	public ResponseEntity<Flights> showByFlightId(@PathVariable Long flightId) throws FlightNotFound {
		log.info("It reciving flight by Id {}",flightId);
		Flights f = service.showByFlightId(flightId);
		return ResponseEntity.status(HttpStatus.OK).body(f);
	}
	
	@GetMapping("{source}/{destination}/{date}")
	public ResponseEntity<List<Flights>> showBySourceAndDestinationAndDate(@PathVariable String source,@PathVariable String destination ,@PathVariable("date") LocalDate departureDate){
		log.info("It reciving flight by source and destination and date ");
		List<Flights> f = service.showBySourceAndDestinationAndDate(source,destination,departureDate);
		return ResponseEntity.status(HttpStatus.OK).body(f);
	}
	
	@PostMapping("add")
	public Flights add(@RequestBody Flights flights) {
		log.info("It is adding the data");
		return service.add(flights);
	}
	
	@PutMapping("update")
	public Flights update(@RequestBody Flights flights) {
		log.info("It is updating the data");
		return service.update(flights);
	}
	
	@GetMapping("prices")
	public Double showPrices(@RequestParam Long flightId,@RequestParam String seatClass) throws FlightNotFound, SeatNotFound {
		log.info("Retriving prices as per seat class");
		return service.showPrices(flightId,seatClass);
	}
	
	@GetMapping("seats")
	public Integer showSeats(@RequestParam Long flightId) throws FlightNotFound {
		log.info("Retriving total number of seats");
		return service.showSeats(flightId);
	}
	
	@GetMapping("flightNo")
	public String showFlightNo(@RequestParam String source,@RequestParam String destination) {
		return service.showFlightNo(source,destination);
	}
	
	@GetMapping("flights")
	public Flights showFlightsByFlightNo(@RequestParam String flightNo) {
		return service.showFlightsByFlightNo(flightNo);
	}
	
	@GetMapping("flightId")
	public long showFlightID(@RequestParam String source,@RequestParam String destination ,@RequestParam LocalDate departureDate) {
		log.info("Retriving flight Id by using source destination and date");
		return service.showFlightID(source,destination,departureDate);
	}
	
	@PutMapping("updateSeats")
	public void updateSeats(@RequestParam Long flightId,@RequestParam Integer updatedSeats) throws FlightNotFound {
		service.updateSeats(flightId,updatedSeats);
	}

}
