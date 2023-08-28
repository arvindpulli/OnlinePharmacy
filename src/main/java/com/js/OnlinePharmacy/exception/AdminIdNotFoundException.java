package com.js.OnlinePharmacy.exception;

public class AdminIdNotFoundException extends RuntimeException {

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public AdminIdNotFoundException(String msg) {
		super();
		this.msg = msg;
	}
	
	
}
