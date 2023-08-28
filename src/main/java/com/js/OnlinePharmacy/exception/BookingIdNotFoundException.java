package com.js.OnlinePharmacy.exception;

import lombok.Getter;

@Getter
public class BookingIdNotFoundException extends RuntimeException {

	private String msg;

	public BookingIdNotFoundException(String msg) {
		super();
		this.msg = msg;
	}
	
}
