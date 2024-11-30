package com.bidding.vendor.exception;

public class VendorAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VendorAlreadyExistsException(String errorMssg) {
		super(errorMssg);
	}
}
