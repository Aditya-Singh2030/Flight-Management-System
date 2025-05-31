package com.case_study.Bookings.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Passenges {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long passengerId;
	private String fullName;
	private String email;
	private String phoneNo;
	@ManyToOne
	@JoinColumn(name="booking_id",nullable = false)
	@JsonIgnore
	private Booking booking;
	
	public Passenges() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Passenges(Long passengerId, String fullName, String email, String phoneNo) {
		super();
		this.passengerId = passengerId;
		this.fullName = fullName;
		this.email = email;
		this.phoneNo = phoneNo;
	}

	public Long getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	
	
	
}
