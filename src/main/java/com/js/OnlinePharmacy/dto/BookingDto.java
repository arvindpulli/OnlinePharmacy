package com.js.OnlinePharmacy.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BookingDto {
	private int bookingId;
	private LocalDate orderDate;
	private int quantity;
	private String paymentMethod;
	private LocalDate expectedDate;
}
