//package com.case_study.Flights.controller;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import com.case_study.Flights.entity.Flights;
//import com.case_study.Flights.exceptions.FlightNotFound;
//import com.case_study.Flights.service.FlightService;
//
//class FlightControllerTest {
//
//	@Mock
//    private FlightService flightService;
//
//    @InjectMocks
//    private FlightController flightController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testShow() {
//        Flights flight1 = new Flights();
//        Flights flight2 = new Flights();
//        List<Flights> flights = Arrays.asList(flight1, flight2);
//
//        when(flightService.show()).thenReturn(flights);
//
//        List<Flights> response = flightController.show();
//
//        assertEquals(HttpStatus.OK, ((ResponseEntity<Flights>) response).getStatusCode());
//        assertEquals(2, ((List<Flights>) ((HttpEntity<Flights>) response).getBody()).size());
//        verify(flightService).show();
//    }
//
//    @Test
//    void testShowByFlightId_Success() throws FlightNotFound {
//        Flights flight = new Flights();
//        flight.setFlightId(1L);
//
//        when(flightService.showByFlightId(1L)).thenReturn(flight);
//
//        ResponseEntity<Flights> response = flightController.showByFlightId(1L);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(1L, response.getBody().getFlightId());
//        verify(flightService).showByFlightId(1L);
//    }
//
//    @Test
//    void testShowByFlightId_NotFound() throws FlightNotFound {
//        when(flightService.showByFlightId(1L)).thenThrow(new FlightNotFound("Flight not found"));
//
//        ResponseEntity<Flights> response = flightController.showByFlightId(1L);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        verify(flightService).showByFlightId(1L);
//    }
//
//    @Test
//    void testShowBySourceAndDestinationAndDate() {
//        Flights flight = new Flights();
//        List<Flights> flights = Arrays.asList(flight);
//        
//        LocalDate date = LocalDate.of(2025, 4, 3);
//        
//        when(flightService.showBySourceAndDestinationAndDate("NYC", "LAX", date)).thenReturn(flights);
//
//        ResponseEntity<List<Flights>> response = flightController.showBySourceAndDestinationAndDate("NYC", "LAX", date);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(1, response.getBody().size());
//        verify(flightService).showBySourceAndDestinationAndDate("NYC", "LAX", date);
//    }
//
//    @Test
//    void testAdd() {
//        Flights flight = new Flights();
//        flight.setFlightId(1L);
//
//        when(flightService.add(any(Flights.class))).thenReturn(flight);
//
//        Flights response = flightController.add(flight);
//
//        assertEquals(HttpStatus.CREATED, response.getStatus());
//        assertEquals(1L, ((Flights) ((Flights) response).getBody()).getFlightId());
//        verify(flightService).add(any(Flights.class));
//    }
//
//    @Test
//    void testUpdate() {
//        Flights flight = new Flights();
//        flight.setFlightId(1L);
//
//        when(flightService.update(any(Flights.class))).thenReturn(flight);
//
//        Flights response = flightController.update(flight);
//
//        assertEquals(HttpStatus.OK, response.getStatus());
//        assertEquals(1L, ((Flights) ((Flights) response).getBody()).getFlightId());
//        verify(flightService).update(any(Flights.class));
//    }
//
//}
