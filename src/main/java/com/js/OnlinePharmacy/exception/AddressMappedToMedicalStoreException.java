package com.js.OnlinePharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter

public class AddressMappedToMedicalStoreException extends Exception {

	private String msg;

	public AddressMappedToMedicalStoreException(String msg) {
		super();
		this.msg = msg;
	}
	
}
