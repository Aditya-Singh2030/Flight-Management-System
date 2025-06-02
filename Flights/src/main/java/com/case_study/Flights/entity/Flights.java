package com.case_study.Flights.entity;

import java.beans.Transient;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Flights {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long flightId;
	private String airline;
	private String flightNo;
	private String source;
	private String destination;
	private LocalDate departureDate;
	private LocalTime departureTime;
	private LocalDate arrivalDate;
	private LocalTime arrivalTime;
	private Integer totalSeats;
	private String status;
	@OneToMany(mappedBy = "flights",cascade = CascadeType.ALL)
	private List<Seat> seats;
	public Flights() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Flights(Long flightId, String airline, String flightNo, String source, String destination,
			LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime,
			Integer totalSeats, String status, List<Seat> seats) {
		super();
		this.flightId = flightId;
		this.airline = airline;
		this.flightNo = flightNo;
		this.source = source;
		this.destination = destination;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.totalSeats = totalSeats;
		this.status = status;
		this.seats = seats;
	}
	public Long getFlightId() {
		return flightId;
	}
	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
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
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	public LocalDate getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Integer getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Seat> getSeats() {
		return seats;
	}
	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	
	
	
	
}
