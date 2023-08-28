package com.js.OnlinePharmacy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

	private int addressId;
	private int plotNumber;
	private String streetName;
	private String city;
	private String state;
	private long pincode;
}
