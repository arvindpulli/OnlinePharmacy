package com.js.OnlinePharmacy.exception;

import lombok.Getter;

@Getter
public class MedicineIdNotFoundException extends RuntimeException {

	private String msg;

	public MedicineIdNotFoundException(String msg) {
		super();
		this.msg = msg;
	}
	
}
