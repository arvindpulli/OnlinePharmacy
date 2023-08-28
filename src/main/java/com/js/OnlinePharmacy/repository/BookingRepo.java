package com.js.OnlinePharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.OnlinePharmacy.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer> {

}
