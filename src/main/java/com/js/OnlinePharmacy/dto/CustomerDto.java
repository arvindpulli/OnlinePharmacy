package com.js.OnlinePharmacy.dto;

import java.util.List;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerDto {

	private int customerId;
	private String customerName;
	private String customerEmail;
	private long customerPhone;
	
	@OneToMany
	private List<AddressDto>addresses;
	
	@OneToMany
	private List<BookingDto> bookingsDtos;
}
