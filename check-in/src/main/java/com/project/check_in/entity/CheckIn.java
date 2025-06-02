package com.project.check_in.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class CheckIn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long checkInId;
	private Long bookingId;
	private String flightNo;
	private LocalDateTime checkInTime;
	private String status;
	@OneToMany(mappedBy = "checkIn",cascade = CascadeType.ALL)
	private List<SeatAssign> seatAssign;
	public CheckIn() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CheckIn(Long checkInId, Long bookingId, String flightNo,
			LocalDateTime checkInTime, String status) {
		super();
		this.checkInId = checkInId;
		this.bookingId = bookingId;
		this.flightNo = flightNo;
		this.checkInTime = checkInTime;
		this.status = status;
	}
	public Long getCheckInId() {
		return checkInId;
	}
	public void setCheckInId(Long checkInId) {
		this.checkInId = checkInId;
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
	public LocalDateTime getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(LocalDateTime checkInTime) {
		this.checkInTime = checkInTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<SeatAssign> getSeatAssign() {
		return seatAssign;
	}
	public void setSeatAssign(List<SeatAssign> seatAssign) {
		this.seatAssign = seatAssign;
	}
	
	
	
}
