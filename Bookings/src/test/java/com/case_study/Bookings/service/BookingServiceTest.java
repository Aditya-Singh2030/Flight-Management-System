package com.case_study.Bookings.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import com.case_study.Bookings.dao.BookingDao;
import com.case_study.Bookings.dao.PassengeDao;
import com.case_study.Bookings.entity.Booking;
import com.case_study.Bookings.entity.Passenges;
import com.case_study.Bookings.exception.BookingNotFound;
import com.case_study.Bookings.exception.NoSeats;
import com.case_study.Bookings.feing.FlightFeing;

class BookingServiceTest {

	@Mock
    private BookingDao repo;

    @Mock
    private PassengeDao passRepo;

    @Mock
    private FlightFeing flight;

    @Mock
    private Logger log;

    @InjectMocks
    private BookingService bookingService;

    private Booking booking;
    private List<Passenges> passengers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        booking = new Booking();
        booking.setSource("NYC");
        booking.setDestination("LAX");
        booking.setDepartureDate(LocalDate.now());
        booking.setSeatClass("economy");
        booking.setNoOfPassengers(3);
        booking.setPassenger(new ArrayList<>());

        passengers = new ArrayList<>();
        passengers.add(new Passenges());
        passengers.add(new Passenges());
        passengers.add(new Passenges());

        booking.setPassenger(passengers);
    }

    @Test
    void testShow() {
        when(repo.findAll()).thenReturn(List.of(booking));
        List<Booking> result = bookingService.show();
        assertEquals(1, result.size());
    }

    @Test
    void testAddBookingSuccessful() throws NoSeats {
        when(flight.showFlightID(toString(), toString(), any(LocalDate.class))).thenReturn(1L);
        when(flight.showSeats(1L)).thenReturn(100);
        when(flight.showPrices(1L, "economy")).thenReturn(200.0);
        when(repo.save(any(Booking.class))).thenReturn(booking);

        Booking savedBooking = bookingService.add(booking);

        assertEquals(600.0, savedBooking.getTotalCost());
        verify(flight).updateSeats(1L, 97);  // 100 - 3 passengers = 97
    }

    @Test
    void testAddBookingThrowsNoSeats() {
        when(flight.showFlightID(toString(), toString(), any(LocalDate.class))).thenReturn(1L);
        when(flight.showSeats(1L)).thenReturn(2);  // Less than number of passengers

        assertThrows(NoSeats.class, () -> bookingService.add(booking));
    }

    @Test
    void testGetByIdSuccessful() throws BookingNotFound {
        when(repo.findById(1L)).thenReturn(Optional.of(booking));

        Booking result = bookingService.getById(1L);

        assertNotNull(result);
    }

    @Test
    void testGetByIdThrowsBookingNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BookingNotFound.class, () -> bookingService.getById(1L));
    }

    @Test
    void testDeleteById() {
        doNothing().when(repo).deleteById(1L);

        bookingService.deleteById(1L);

        verify(repo, times(1)).deleteById(1L);
    }

}
