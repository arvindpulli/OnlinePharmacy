package com.js.OnlinePharmacy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.js.OnlinePharmacy.Enum.BookingStatus;
import com.js.OnlinePharmacy.dao.BookingDao;
import com.js.OnlinePharmacy.dao.CustomerDao;
import com.js.OnlinePharmacy.dao.MedicineDao;
import com.js.OnlinePharmacy.dto.BookingDto;
import com.js.OnlinePharmacy.entity.Booking;
import com.js.OnlinePharmacy.entity.Customer;
import com.js.OnlinePharmacy.entity.Medicine;
import com.js.OnlinePharmacy.exception.BookingAlreadyCancelledException;
import com.js.OnlinePharmacy.exception.BookingCantCancelledNow;
import com.js.OnlinePharmacy.exception.BookingDeliveredException;
import com.js.OnlinePharmacy.exception.BookingIdNotFoundException;
import com.js.OnlinePharmacy.exception.CustomerIdNotFoundException;
import com.js.OnlinePharmacy.exception.MedicineIdNotFoundException;
import com.js.OnlinePharmacy.util.ResponseStructure;

@Service
public class BookingService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private MedicineDao medicineDao;

	public ResponseEntity<ResponseStructure<Booking>> saveBooking(int customerId, int medicineId,
		BookingDto bookingDto) {
		Booking booking=this.modelMapper.map(bookingDto, Booking.class);
		Customer dbCustomer=customerDao.findCustomer(customerId);
		if(dbCustomer!=null) {
			Medicine dbMedicine=medicineDao.findMedicine(medicineId);
			if(dbMedicine!=null) {
				List<Medicine>medicines=new ArrayList<Medicine>();
				medicines.add(dbMedicine);
				booking.setCustomer(dbCustomer);
				booking.setMedicines(medicines);
				booking.setBookingStatus(BookingStatus.ACTIVE);
				
				List<Booking>bookings=new ArrayList<Booking>();
				bookings.add(booking);
				   
				dbCustomer.setBookings(bookings);
				customerDao.updateCustomer(customerId, dbCustomer);
				//updating stock
				dbMedicine.setStockQuantity(dbMedicine.getStockQuantity()-booking.getQuantity());
				
				Booking dbBooking=bookingDao.saveBooking(booking);
				
				
				ResponseStructure<Booking>structure=new ResponseStructure<Booking>();
				structure.setMessage("Booking added successfully !");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(dbBooking);
				
				return new ResponseEntity<ResponseStructure<Booking>>(structure,HttpStatus.CREATED);
			}else {
				throw new MedicineIdNotFoundException("Sorry ! failed to add booking");
			}
		}else {
			throw new CustomerIdNotFoundException("Sorry ! failed to add booking");
		}
	}

	
	
	
	
	public ResponseEntity<ResponseStructure<Booking>> cancelBooking(int bookingId) {
		Booking dbBooking=bookingDao.findBooking(bookingId);
		
		LocalDate cancelledDay=dbBooking.getExpectedDate().minusDays(2);
		
		//expected date =24
		//cancelled Date=24-2=22
		
		
		if(dbBooking!=null) {
			if(dbBooking.getBookingStatus().equals(BookingStatus.CANCELLED)) {
				throw new BookingAlreadyCancelledException("Sorry ! this booking is already cancelled");
			}
			else if(dbBooking.getBookingStatus().equals(BookingStatus.DELIVERED)) {
				throw new BookingDeliveredException("Sorry ! can't cancel this booking, it's already delivered");
			}
			else if(LocalDate.now().equals(cancelledDay)|| LocalDate.now().isAfter(cancelledDay)) {
				throw new BookingCantCancelledNow("Sorry ! booking cant't cancelled Now because expected date is near");
			}
			else {
				Booking cancelledBooking=bookingDao.cancelBooking(bookingId);
				ResponseStructure<Booking>structure=new ResponseStructure<Booking>();
				structure.setMessage("Booking cancelled successfully !");
				structure.setStatus(HttpStatus.GONE.value());
				structure.setData(dbBooking);
				return new ResponseEntity<ResponseStructure<Booking>>(structure,HttpStatus.GONE);
			}
		}
		else {
			throw new BookingIdNotFoundException("Sorry ! failed to cancel the booking");
		}
	}
	
	
	
	
	
}
