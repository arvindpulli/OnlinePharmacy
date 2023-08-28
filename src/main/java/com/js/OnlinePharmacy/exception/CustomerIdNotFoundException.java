package com.js.OnlinePharmacy.exception;

import lombok.Getter;

@Getter
public class CustomerIdNotFoundException extends RuntimeException {

	private String msg;

	public CustomerIdNotFoundException(String msg) {
		super();
		this.msg = msg;
	}
	
}
