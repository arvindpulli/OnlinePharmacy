package com.js.OnlinePharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.js.OnlinePharmacy.Enum.BookingStatus;
import com.js.OnlinePharmacy.entity.Booking;
import com.js.OnlinePharmacy.repository.BookingRepo;

@Repository
public class BookingDao {

	@Autowired
	private BookingRepo repo;
	
	public Booking saveBooking(Booking booking) {
		return repo.save(booking);
	}

	public Booking cancelBooking(int bookingId) {
		Optional<Booking>optional=repo.findById(bookingId);
		if(optional.isPresent()) {
			optional.get().setBookingStatus(BookingStatus.CANCELLED);
			return repo.save(optional.get());
			//cancel but it should not delete from database
		}
		return null;
	}
	
	public Booking findBooking(int bookingId) {
		Optional<Booking>optional=repo.findById(bookingId);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}
	
	
}
