package com.case_study.Flights.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seatId;
	private String seatNo;
	private String seatClass;
	private Double price;
	private String seatStatus;
//	@Transient
	@ManyToOne
	@JoinColumn(name="flight_id",nullable = false)
	@JsonIgnore
	private Flights flights;
	
	public Seat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seat(Long seatId, String seatNo, String seatClass, Double price, String seatStatus) {
		super();
		this.seatId = seatId;
		this.seatNo = seatNo;
		this.seatClass = seatClass;
		this.price = price;
		this.seatStatus = seatStatus;
	}

	public Long getSeatId() {
		return seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(String seatStatus) {
		this.seatStatus = seatStatus;
	}

	public Flights getFlights() {
		return flights;
	}

	public void setFlights(Flights flights) {
		this.flights = flights;
	}
	
	
	
}
