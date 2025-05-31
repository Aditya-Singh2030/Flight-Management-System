package com.case_study.Bookings.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.case_study.Bookings.entity.Passenges;

@Repository
public interface PassengeDao extends JpaRepository<Passenges, Long>{

	List<Passenges> findByBooking_BookingId(long bookingId);

}
