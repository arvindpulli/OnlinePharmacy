package com.js.OnlinePharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreIsAlreadyMappedToStaff extends RuntimeException {

	private String msg;
}
