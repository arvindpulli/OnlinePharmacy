package com.js.OnlinePharmacy.exception;

public class MedicalStoreIdNotFoundException extends RuntimeException {

	private String msg;

	public MedicalStoreIdNotFoundException(String msg) {
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
