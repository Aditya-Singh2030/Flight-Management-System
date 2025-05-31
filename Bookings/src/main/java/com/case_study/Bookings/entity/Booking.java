package com.case_study.Bookings.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;
	private String flightNo;
	private String source;
	private String destination;
	private LocalDate departureDate;
	private Integer noOfPassengers;
	private LocalDateTime bookedAt;
	private String seatClass;
	private Double totalCost;
	@OneToMany(mappedBy = "booking",cascade = CascadeType.ALL)
	private List<Passenges> passenger;
	
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Booking(Long bookingId, String flightNo, String source, String destination, LocalDate departureDate,
			Integer noOfPassengers, LocalDateTime bookedAt, String seatClass, Double totalCost,
			List<Passenges> passenger) {
		super();
		this.bookingId = bookingId;
		this.flightNo = flightNo;
		this.source = source;
		this.destination = destination;
		this.departureDate = departureDate;
		this.noOfPassengers = noOfPassengers;
		this.bookedAt = bookedAt;
		this.seatClass = seatClass;
		this.totalCost = totalCost;
		this.passenger = passenger;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public Integer getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(Integer noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public LocalDateTime getBookedAt() {
		return bookedAt;
	}

	public void setBookedAt(LocalDateTime bookedAt) {
		this.bookedAt = bookedAt;
	}

	public String getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public List<Passenges> getPassenger() {
		return passenger;
	}

	public void setPassenger(List<Passenges> passenger) {
		this.passenger = passenger;
	}

	
	
}
