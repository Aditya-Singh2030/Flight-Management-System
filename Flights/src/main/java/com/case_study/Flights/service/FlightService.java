package com.case_study.Flights.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.case_study.Flights.controller.FlightController;
import com.case_study.Flights.dao.FlightDao;
import com.case_study.Flights.dao.SeatDao;
import com.case_study.Flights.entity.Flights;
import com.case_study.Flights.entity.Seat;
import com.case_study.Flights.exceptions.FlightNotFound;
import com.case_study.Flights.exceptions.SeatNotFound;

@Service
public class FlightService {
	
	@Autowired
	private FlightDao repo;
	@Autowired
	private SeatDao seatRepo;
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(FlightService.class);

	
	public List<Flights> show() {
		// TODO Auto-generated method stub
		log.info("it is showing all the flights that are in the data base");
		return repo.findAll();
	}


	public Flights add(Flights flights) {
		// TODO Auto-generated method stub
		log.info("It is adding the data");
		
		if(flights.getSeats()!=null) {
			for(Seat seats :flights.getSeats()) {
				seats.setFlights(flights);
			}
		}
		
		return repo.save(flights);
		
	}


	public Flights showByFlightId(Long flightId) throws FlightNotFound {
		// TODO Auto-generated method stub
		log.info("It reciving flight by Id {}",flightId);
		Flights f = repo.findById(flightId).orElseThrow(()->new FlightNotFound("This Flight id is not here " + flightId));
		return f;
	}


	public List<Flights> showBySourceAndDestinationAndDate(String source, String destination, LocalDate departureDate) {
		// TODO Auto-generated method stub
		log.info("It reciving flight by source and destination and date ");
		List<Flights> f = repo.findBySourceAndDestinationAndDepartureDate(source,destination,departureDate);
		return f;
	}


	public Flights update(Flights flights) {
		log.info("It is updating the data");
		if(flights.getSeats()!=null) {
			for(Seat seats :flights.getSeats()) {
				seats.setFlights(flights);
			}
		}
		
		return repo.save(flights);
	}


//	public Seat showPrices(Flights flight, String seatClass) {
//		// TODO Auto-generated method stub
//		return seatRepo.findByFlightAndSeatClass(flight,seatClass);
//	}


//	public Double showPrices(Flights flights, String seatClass) {
//		
////		return seatRepo.findPriceByFlightsAndSeatClass(flights,seatClass);
 
//		
//		Flights flight = repo.getFlightById(flightId);  // Fetch the Flights object using the flightId
//        if (flights == null) {
//            throw new RuntimeException("Flight not found with ID: " + flightId);
//        }
//        Double price = seatDao.findPriceByFlightsAndSeatClass(flights, seatClass);
//        if (price == null) {
//            throw new RuntimeException("No price found for the specified seat class.");
//        }
//        return price;
//	}


	public Double showPrices(Long flightId, String seatClass) throws FlightNotFound, SeatNotFound {
		Flights flights = showByFlightId(flightId);  
        if (flights == null) {
            throw new FlightNotFound("Flight not found with ID: " + flightId);
        }
        Double price = seatRepo.findPriceByFlightsAndSeatClass(flights, seatClass);
        if (price == null) {
            throw new SeatNotFound("No price found for the specified seat class.");
        }
        return price;
	}


public Integer showSeats(Long flightId) throws FlightNotFound {
	// TODO Auto-generated method stub
	Flights flights = showByFlightId(flightId); 
    if (flights == null) {
        throw new FlightNotFound("Flight not found with ID: " + flightId);
    }
    
	return repo.findTotalSeatsByFlightId(flightId);
}


public long showFlightID(String source, String destination, LocalDate departureDate) {
	// TODO Auto-generated method stub
	return repo.findFlightIdBySourceAndDestinationAndDepartureDate(source,destination,departureDate);
}


public void updateSeats(Long flightId, Integer updatedSeats) throws FlightNotFound {
	// TODO Auto-generated method stub
	Flights flight = showByFlightId(flightId);

    // Update the total seats
    flight.setTotalSeats(updatedSeats);

    // Save the updated flight
    repo.save(flight);
}


public String showFlightNo(String source, String destination) {
	// TODO Auto-generated method stub
	return repo.findFlightNoBySourceAndDestination(source,destination);
}


public Flights showFlightsByFlightNo(String flightNo) {
	// TODO Auto-generated method stub
	return repo.findFlightsByFlightNo(flightNo);
}


	

}
