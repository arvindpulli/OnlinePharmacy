package com.js.OnlinePharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class PhoneNumberIsNotValidException extends RuntimeException {

	private String msg;
	
}
