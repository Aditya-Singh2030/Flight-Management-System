package com.case_study.Flights.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.case_study.Flights.dao.FlightDao;
import com.case_study.Flights.entity.Flights;
import com.case_study.Flights.entity.Seat;
import com.case_study.Flights.exceptions.FlightNotFound;

class FlightServiceTest {

	@Mock
    private FlightDao repo;

    @InjectMocks
    private FlightService flightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShow() {
        Flights flight1 = new Flights();
        Flights flight2 = new Flights();
        List<Flights> flights = Arrays.asList(flight1, flight2);

        when(repo.findAll()).thenReturn(flights);

        List<Flights> result = flightService.show();

        assertEquals(2, result.size());
        verify(repo).findAll();
    }

    @Test
    void testAdd() {
        Flights flight = new Flights();
        Seat seat = new Seat();
        flight.setSeats(Arrays.asList(seat));

        when(repo.save(flight)).thenReturn(flight);

        Flights result = flightService.add(flight);

        assertEquals(flight, result);
        assertEquals(flight, seat.getFlights());
        verify(repo).save(flight);
    }

    @Test
    void testShowByFlightId_Success() throws FlightNotFound {
        Flights flight = new Flights();
        flight.setFlightId(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(flight));

        Flights result = flightService.showByFlightId(1L);

        assertEquals(1L, result.getFlightId());
        verify(repo).findById(1L);
    }

    @Test
    void testShowByFlightId_NotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        FlightNotFound exception = assertThrows(FlightNotFound.class, () -> {
            flightService.showByFlightId(1L);
        });

        assertEquals("This Flight id is not here 1", exception.getMessage());
        verify(repo).findById(1L);
    }

    @Test
    void testShowBySourceAndDestinationAndDate() {
        Flights flight = new Flights();
        List<Flights> flights = Arrays.asList(flight);

        LocalDate date = LocalDate.of(2025, 4, 3);
        
        when(repo.findBySourceAndDestinationAndDepartureDate("NYC", "LAX", date)).thenReturn(flights);

        List<Flights> result = flightService.showBySourceAndDestinationAndDate("NYC", "LAX", date);

        assertEquals(1, result.size());
        verify(repo).findBySourceAndDestinationAndDepartureDate("NYC", "LAX", date);
    }

    @Test
    void testUpdate() {
        Flights flight = new Flights();
        flight.setFlightId(1L);
        Seat seat = new Seat();
        flight.setSeats(Arrays.asList(seat));

        when(repo.save(flight)).thenReturn(flight);

        Flights result = flightService.update(flight);

        assertEquals(flight, result);
        assertEquals(flight, seat.getFlights());
        verify(repo).save(flight);
    }

}
