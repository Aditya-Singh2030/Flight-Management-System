package com.project.check_in.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SeatAssign {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long assingId;
	private Long passengerId;
	private String name;
	private String seatNo;
	private String seatStatus;
	@ManyToOne
	@JoinColumn(name="check_in_id")
	@JsonIgnore
	private CheckIn checkIn;
	public CheckIn getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(CheckIn checkIn) {
		this.checkIn = checkIn;
	}
	public SeatAssign() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SeatAssign(Long assingId, Long passengerId, String name, String seatNo,
			String seatStatus) {
		super();
		this.assingId = assingId;
		this.passengerId = passengerId;
		this.name = name;
		this.seatNo = seatNo;
		this.seatStatus = seatStatus;
	}
	public Long getAssingId() {
		return assingId;
	}
	public void setAssingId(Long assingId) {
		this.assingId = assingId;
	}
	public Long getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public String getSeatStatus() {
		return seatStatus;
	}
	public void setSeatStatus(String seatStatus) {
		this.seatStatus = seatStatus;
	}
	
	

}
