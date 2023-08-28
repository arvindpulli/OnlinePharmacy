package com.js.OnlinePharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.js.OnlinePharmacy.dto.BookingDto;
import com.js.OnlinePharmacy.entity.Booking;
import com.js.OnlinePharmacy.service.BookingService;
import com.js.OnlinePharmacy.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private BookingService service;

	@Operation(summary =  "Save Booking",  description ="This API is used to save the Booking")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Successfully created")})
	@PostMapping
	public ResponseEntity<ResponseStructure<Booking>>saveBooking(@RequestParam int medicineId,@RequestParam int customerId,@RequestBody BookingDto bookingDto){
		return service.saveBooking(customerId,medicineId,bookingDto);
	}
	
	
	@Operation(summary = "Cancel Booking", description ="This API is used to Cancel the existing Booking")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Successfully deleted"),
			@ApiResponse(responseCode = "404",description = "In not Found for Booking")})	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Booking>>cancelBooking(@RequestParam int bookingId){
		return service.cancelBooking(bookingId);
	}
	
	
}

