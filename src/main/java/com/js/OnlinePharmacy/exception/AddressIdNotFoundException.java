package com.js.OnlinePharmacy.exception;

public class AddressIdNotFoundException extends RuntimeException {

	private String msg;

	public AddressIdNotFoundException(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
