package com.js.OnlinePharmacy.exception;

import lombok.Getter;

@Getter
public class AddressAlreayMappedToOtherCustomerException extends RuntimeException {
	private String msg;

	public AddressAlreayMappedToOtherCustomerException(String msg) {
		super();
		this.msg = msg;
	}

	

}
