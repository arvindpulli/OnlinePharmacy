package com.js.OnlinePharmacy.exception;

import lombok.Getter;

@Getter
public class StaffIdNotFoundException extends RuntimeException {

	private String msg;

	public StaffIdNotFoundException(String msg) {
		super();
		this.msg = msg;
	}
	
}
